package Chess;

import javafx.scene.input.KeyCombination;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private Piece selPiece;
	private boolean check, checkmate, end;

	private boolean Save[][] = new boolean[8][8];
	private boolean Movable[][] = new boolean[8][8];
	private boolean King[][] = new boolean[8][8];
	private int turn = 1;


	public void movable(Piece piece, int px, int py){
		switch(piece.type){
			case pawn:
				if(piece.color==PlayerColor.black) { // px+1 >= 8인 경우 null
					if(px+1<8 && getIcon(px+1, py).type.equals(PieceType.none)){
							//markPosition(px+1, py);
							Movable[px+1][py] = true;
						}
					if(turn==1){ //black's first turn
						//markPosition(px+2, py);
						Movable[px+2][py] = true;
					}
					if(py-1>=0 && px+1<8 && getIcon(px+1, py-1).type!=PieceType.none && getIcon(px+1, py-1).color!=PlayerColor.black){
						//markPosition(px+1, py-1);
						Movable[px+1][py-1] = true; King[px+1][py-1] = true;
					}
					if(py+1<8 && px+1<8 && getIcon(px+1, py+1).type!=PieceType.none && getIcon(px+1, py+1).color!=PlayerColor.black){
						//markPosition(px+1, py+1);
						Movable[px+1][py+1] = true; King[px+1][py+1] = true;
					}


				}
				else if(piece.color==PlayerColor.white){
						if(px-1>=0 && getIcon(px-1, py).type.equals(PieceType.none)){
							//markPosition(px-1, py);
							Movable[px-1][py] = true;
						}

						if(turn==2){ //white's first turn
							//markPosition(px-2, py);
							Movable[px-2][py] = true;
						}

						if(py-1>=0 && px-1>=0 && getIcon(px-1, py-1).type!=PieceType.none && getIcon(px-1, py-1).color!=PlayerColor.white){
							//markPosition(px-1, py-1);
							Movable[px-1][py-1] = true; King[px-1][py-1] = true;
						}
						if(py+1<8 && px-1>=0 && getIcon(px-1, py+1).type!=PieceType.none && getIcon(px-1, py+1).color!=PlayerColor.white){
							//markPosition(px-1, py+1);
							Movable[px-1][py+1] = true; King[px-1][py+1] = true;
						}
				}
				break;

			case rook:
				loop_r1:
				for(int i=px-1; i>=0; i--){
					if(getIcon(i, py).type.equals(PieceType.none)){
						//markPosition(i, py);
						Movable[i][py] = true; King[i][py] = true;
					}
					else{
						if(piece.color!=getIcon(i, py).color) {
							//markPosition(i, py);
							Movable[i][py] = true; King[i][py] = true;
						}
						break loop_r1;
					}
				}

				loop_r2:
				for(int i=px+1; i<8; i++){
					if(getIcon(i, py).type.equals(PieceType.none)){
						//markPosition(i, py);
						Movable[i][py] = true; King[i][py] = true;
					}
					else{
						if(piece.color!=getIcon(i, py).color) {
							//markPosition(i, py);
							Movable[i][py] = true; King[i][py] = true;
						}
						break loop_r2;
					}
				}

				loop_r3:
				for(int j=py-1; j>=0; j--){
					if(getIcon(px, j).type.equals(PieceType.none)){
						//markPosition(px, j);
						Movable[px][j] = true; King[px][j] = true;
					}

					else{
						if(piece.color!=getIcon(px, j).color) {
						 	//markPosition(px+1, j);
							Movable[px+1][j] = true; King[px+1][j] = true;
						}
						break loop_r3;
					}
				}

				loop_r4:
				for(int j=py+1; j<8; j++){
					if(getIcon(px, j).type.equals(PieceType.none)){
						//markPosition(px, j);
						Movable[px][j] = true; King[px][j] = true;
					}

					else{
						if(piece.color!=getIcon(px, j).color) {
							//markPosition(px+1, j);
							Movable[px+1][j] = true; King[px+1][j] = true;
						}
						break loop_r4;
					}
				}
				break;

			case knight:
					for(int i=0; i<8; i++){
						for(int j=0; j<8; j++){
							if((i-px)*(j-py)==2 || (i-px)*(j-py)==-2){
								if(getIcon(i,j).type.equals((PieceType.none))){
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								else if(getIcon(i, j).color != piece.color){
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
							}
						}
					}
				break;

			case bishop:
				int a=1, b=1, c=1, d=1;

				loop_b1:
				for(int i=px; i>=0; i--){
					for(int j=py; j>=0; j--){
						if(i==px-a && j==py-a)
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								a++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_b1;
						}
					}
				}

				loop_b2:
				for(int i=px; i>=0; i--){
					for(int j=py; j<8; j++){
						if(i==px-b && j==py+b) {
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								b++;
							}

							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_b2;
							}
						}
					}
				}

				loop_b3:
				for(int i=px; i<8; i++){
					for(int j=py; j>=0; j--){
						if(i==px+c && j==py-c){
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								c++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_b3;
							}
						}
					}
				}

				loop_b4:
				for(int i=px; i<8; i++){
					for(int j=py; j<8; j++){
						if(i==px+d && j==py+d){
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								d++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_b4;
							}
						}
					}
				}
				break;

			case king:
				// king_cannot_go(piece.color);
				for(int i=0; i<8; i++){
					for(int j=0; j<8; j++){
						if(Math.abs(i-px)<=1 && Math.abs(j-py)<=1){
							if(getIcon(i, j).type.equals(PieceType.none) || getIcon(i,j).color!= piece.color) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
							}
							/*if(King[i][j]==true){
								Movable[i][j] = false;
							}*/
						}
					}
				}

				where_king_cannot_go(piece.color, px, py);
				break;

			case queen:
				a=1; b=1; c=1; d=1;

				loop_q1:
				for(int i=px; i>=0; i--){
					for(int j=py; j>=0; j--){
						if(i==px-a && j==py-a)
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								a++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_q1;
							}
					}
				}

				loop_q2:
				for(int i=px; i>=0; i--){
					for(int j=py; j<8; j++){
						if(i==px-b && j==py+b) {
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								b++;
							}

							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_q2;
							}
						}
					}
				}

				loop_q3:
				for(int i=px; i<8; i++){
					for(int j=py; j>=0; j--){
						if(i==px+c && j==py-c){
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								c++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_q3;
							}
						}
					}
				}

				loop_q4:
				for(int i=px; i<8; i++){
					for(int j=py; j<8; j++){
						if(i==px+d && j==py+d){
							if(getIcon(i, j).type.equals(PieceType.none)) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
								d++;
							}
							else{
								if(piece.color!=getIcon(i, j).color) {
									//markPosition(i, j);
									Movable[i][j] = true; King[i][j] = true;
								}
								break loop_q4;
							}
						}
					}
				}

				for(int i=0; i<8; i++){
					for(int j=0; j<8; j++){
						if((Math.abs(px-i)==1 && Math.abs(py-j)==0) || (Math.abs(px-i)==0 && Math.abs(py-j)==1)){
							if(getIcon(i, j).type.equals(PieceType.none) || getIcon(i,j).color!= piece.color) {
								//markPosition(i, j);
								Movable[i][j] = true; King[i][j] = true;
							}
						}
					}
				}
				break;
		}
	}

	public void move_and_capture(Piece piece, int px, int py, int to_x, int to_y){
		Piece temp = new Piece(PlayerColor.none, PieceType.none);
		setIcon(to_x, to_y, piece);
		setIcon(px, py, temp);
	}

	public boolean check(PlayerColor color) { // 수정 필요. 말이 존재하는 경우 pawn
		initMovable();
	    int x = -1; int y = -1;

	    //현재 black's turn, next white's turn
        if (color.equals(PlayerColor.black)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece temp = getIcon(i, j);
                    if (temp.type.equals(PieceType.pawn) && temp.color.equals(PlayerColor.black)) {
                        if (j - 1 >= 0 && i + 1 < 8 && getIcon(j-1, i+1).color!=PlayerColor.black) Movable[i+1][j-1] = true;
                        if (j + 1 < 8 && i + 1 < 8 && getIcon(j+1, i+1).color!=PlayerColor.black) Movable[i+1][j+1] = true;
                    } else if (temp.color.equals(PlayerColor.black)) {
                        movable(temp, i, j);
                    } else if (temp.type.equals(PieceType.king) && temp.color.equals(PlayerColor.white)) {
                        x = j;
                        y = i;
                    }
                }
            }

            if (Movable[y][x] == true) {
                System.out.println(x+" "+y);
                return true;
            } else {
                System.out.println(x+" "+y);
                return false;
            }
        }

        //현재 white turn, next black's turn
        else if (color.equals(PlayerColor.white)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Piece temp = getIcon(i, j);
                    if (temp.type.equals(PieceType.pawn) && temp.color.equals(PlayerColor.white)) {
                        if (j - 1 >= 0 && i - 1 >= 0 && getIcon(j-1, i-1).color!=PlayerColor.black) Movable[i-1][j-1] = true;
                        if (j + 1 < 8 && i - 1 >= 0 && getIcon(j+1, i-1).color!=PlayerColor.black) Movable[i-1][j+1] = true;
                    } else if (temp.color.equals(PlayerColor.white)) {
                        movable(temp, i, j);
                    } else if (temp.type.equals(PieceType.king) && temp.color.equals(PlayerColor.black)) {
                        x = j;
                        y = i;
                        // System.out.println(x+" "+y);
                    }
                }
            }
            if (Movable[y][x] == true) {
                System.out.println(x+" "+y);
                return true;
            } else {
                System.out.println(x+" "+y);
                return false;
            }

        }

        if(x==-1 && y==-1) return false;

        return false;
    }

	public void where_king_can_go(Piece piece, int px, int py) {
	switch (piece.type) {
		case pawn:
			if (piece.color == PlayerColor.black) { // px+1 >= 8인 경우 null
				if (py - 1 >= 0 && px + 1 < 8 && getIcon(px + 1, py - 1).type != PieceType.none && getIcon(px + 1, py - 1).color != PlayerColor.black) {
					King[px + 1][py - 1] = true;
				}
				if (py + 1 < 8 && px + 1 < 8 && getIcon(px + 1, py + 1).type != PieceType.none && getIcon(px + 1, py + 1).color != PlayerColor.black) {
					King[px + 1][py + 1] = true;
				}


			} else if (piece.color == PlayerColor.white) {

				if (py - 1 >= 0 && px - 1 >= 0 && getIcon(px - 1, py - 1).type != PieceType.none && getIcon(px - 1, py - 1).color != PlayerColor.white) {
					King[px - 1][py - 1] = true;
				}
				if (py + 1 < 8 && px - 1 >= 0 && getIcon(px - 1, py + 1).type != PieceType.none && getIcon(px - 1, py + 1).color != PlayerColor.white) {
					King[px - 1][py + 1] = true;
				}
			}
			break;

		case rook:
			loop_r1:
			for (int i = px - 1; i >= 0; i--) {
				if (getIcon(i, py).type.equals(PieceType.none)) {
					King[i][py] = true;
				} else {
					if (piece.color != getIcon(i, py).color) {
						King[i][py] = true;
					}
					break loop_r1;
				}
			}

			loop_r2:
			for (int i = px + 1; i < 8; i++) {
				if (getIcon(i, py).type.equals(PieceType.none)) {
					King[i][py] = true;
				} else {
					if (piece.color != getIcon(i, py).color) {
						King[i][py] = true;
					}
					break loop_r2;
				}
			}

			loop_r3:
			for (int j = py - 1; j >= 0; j--) {
				if (getIcon(px, j).type.equals(PieceType.none)) {
					King[px][j] = true;
				} else {
					if (piece.color != getIcon(px, j).color) {
						King[px + 1][j] = true;
					}
					break loop_r3;
				}
			}

			loop_r4:
			for (int j = py + 1; j < 8; j++) {
				if (getIcon(px, j).type.equals(PieceType.none)) {
					King[px][j] = true;
				} else {
					if (piece.color != getIcon(px, j).color) {
						King[px + 1][j] = true;
					}
					break loop_r4;
				}
			}
			break;

		case knight:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i - px) * (j - py) == 2 || (i - px) * (j - py) == -2) {
						if (getIcon(i, j).type.equals((PieceType.none))) {
							King[i][j] = true;
						} else if (getIcon(i, j).color != piece.color) {
							King[i][j] = true;
						}
					}
				}
			}
			break;

		case bishop:
			int a = 1, b = 1, c = 1, d = 1;

			loop_b1:
			for (int i = px; i >= 0; i--) {
				for (int j = py; j >= 0; j--) {
					if (i == px - a && j == py - a)
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							a++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_b1;
						}
				}
			}

			loop_b2:
			for (int i = px; i >= 0; i--) {
				for (int j = py; j < 8; j++) {
					if (i == px - b && j == py + b) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							b++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_b2;
						}
					}
				}
			}

			loop_b3:
			for (int i = px; i < 8; i++) {
				for (int j = py; j >= 0; j--) {
					if (i == px + c && j == py - c) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							c++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_b3;
						}
					}
				}
			}

			loop_b4:
			for (int i = px; i < 8; i++) {
				for (int j = py; j < 8; j++) {
					if (i == px + d && j == py + d) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							d++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_b4;
						}
					}
				}
			}
			break;

		case king:
			// king_cannot_go(piece.color);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Math.abs(i - px) <= 1 && Math.abs(j - py) <= 1) {
						if (getIcon(i, j).type.equals(PieceType.none) || getIcon(i, j).color != piece.color) {
							King[i][j] = true;
						}
					}
				}
			}
			break;

		case queen:
			a = 1;
			b = 1;
			c = 1;
			d = 1;

			loop_q1:
			for (int i = px; i >= 0; i--) {
				for (int j = py; j >= 0; j--) {
					if (i == px - a && j == py - a)
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							a++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_q1;
						}
				}
			}

			loop_q2:
			for (int i = px; i >= 0; i--) {
				for (int j = py; j < 8; j++) {
					if (i == px - b && j == py + b) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							b++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_q2;
						}
					}
				}
			}

			loop_q3:
			for (int i = px; i < 8; i++) {
				for (int j = py; j >= 0; j--) {
					if (i == px + c && j == py - c) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							c++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_q3;
						}
					}
				}
			}

			loop_q4:
			for (int i = px; i < 8; i++) {
				for (int j = py; j < 8; j++) {
					if (i == px + d && j == py + d) {
						if (getIcon(i, j).type.equals(PieceType.none)) {
							King[i][j] = true;
							d++;
						} else {
							if (piece.color != getIcon(i, j).color) {
								King[i][j] = true;
							}
							break loop_q4;
						}
					}
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((Math.abs(px - i) == 1 && Math.abs(py - j) == 0) || (Math.abs(px - i) == 0 && Math.abs(py - j) == 1)) {
						if (getIcon(i, j).type.equals(PieceType.none) || getIcon(i, j).color != piece.color) {
							King[i][j] = true;
						}
					}
				}
			}
			break;
		}
	}

    public void where_king_cannot_go(PlayerColor color, int px, int py){

		initKing();
		//상대편의 말이 갈 수 있는 범위
		PlayerColor tempColor = (color.equals(PlayerColor.white)) ? PlayerColor.black : PlayerColor.white;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Piece temp = getIcon(j, i);
				if(temp.color.equals(tempColor)) {
					where_king_can_go(temp, j, i);
				}
			}
		}

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(Math.abs(j-px)<=1 && Math.abs(i-py)<=1){
					if(getIcon(j, i).type.equals(PieceType.none) || getIcon(j,i).color == PlayerColor.none) {
						if(King[i][j]==true){
						    Movable[i][j] = false;
                        }
					}
				}
			}
		} //here
	}

	public void isCheckmate(PlayerColor color){

	}
    public boolean checkmate(PlayerColor color){
		initMovable();
		check(color);
		PlayerColor tempColor = (color.equals(PlayerColor.white)) ? PlayerColor.black : PlayerColor.white;
		//

		return false;
    }

	public void initMovable(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Movable[i][j] = false;
			}
		}
	}
	public void initKing(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				King[i][j] = false;
			}
		}
	}

	public void markAll(){
	    for(int i=0; i<8; i++){
	        for(int j=0; j<8; j++){
	            if(Movable[i][j]==true){
	                markPosition(i, j);
                }
            }
        }
    }
	public void unmarkAll(){

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				unmarkPosition(i,j);
			}
		}
	}

	public void printCurrent(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(Movable[i][j]==true){
					System.out.print("true");
				}

				else{
					System.out.print("\t0");
				}
			}
			System.out.println();
		}
		System.out.println("===========================================================");
	}

	class ButtonListener implements ActionListener{

		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {	// Only modify here
			unmarkAll();

			//말이 이동
			if(Movable[x][y]==true){
				move_and_capture(selPiece, selX, selY, x, y);
				initMovable();
				//현재 white turn, 그 다음 차례에서 black is checked
				if(turn%2==0 && check(PlayerColor.white)==true) setStatus("BLACK's turn / CHECK");

				else if(turn%2==0 && check(PlayerColor.white)==false) setStatus("BLACK's turn");

				//현재 white turn, 그 다음 차례에서 black is checked
				else if(turn%2==1 && check(PlayerColor.black)==true) setStatus("WHITE's turn / CHECK");

				else if(turn%2==1 && check(PlayerColor.black)==false) setStatus("WHITE's turn");
				printCurrent();
				initMovable();
				turn++;
			}

			//말의 이동 범위 확인
			else if(turn%2==0 && getIcon(x, y).color.equals(PlayerColor.white)){
				//setStatus("WHITE's turn");
				initMovable();
				movable(getIcon(x, y), x, y);
				if(getIcon(x, y).type.equals(PieceType.king)){

				}
				markAll();
			}

			else if(turn%2==1 && getIcon(x, y).color.equals(PlayerColor.black)){
				//setStatus("BLACK's turn");
				initMovable();
				movable(getIcon(x, y), x, y);
				markAll();
			}


			selX = x;
			selY = y;
			selPiece = getIcon(x, y);
			printCurrent();

		}
	}
	
	void onInitiateBoard(){
		turn = 1;
		setStatus("BLACK's turn");
		// initBoardStatus();
		unmarkAll();
		initMovable();
	}
}