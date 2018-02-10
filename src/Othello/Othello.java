package Othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

/**
 * Created by raman bansal on 7/24/2017.
 */
public class Othello extends JFrame implements ActionListener {
    private JButton[][] buttons;
    public static final int BOARD_Paths = 8;
    private boolean whiteturn = false;

    private enum GameStatus {
        black_wins, white_wins, incomplete, tie;

    }


    public Othello() {
        buttons = new JButton[BOARD_Paths][BOARD_Paths];
        super.setTitle("Othello!!!!!!!!!!!!!!!!!!");
        super.setResizable(true);
        super.setSize(600, 600);

        GridLayout layout = new GridLayout(BOARD_Paths, BOARD_Paths);
        super.setLayout(layout);

        for (int row = 0; row < BOARD_Paths; row++) {
            for (int coll = 0; coll < BOARD_Paths; coll++) {
                JButton button = new JButton();
                buttons[row][coll] = button;
                buttons[row][coll].setBackground(green);
                if (row == 3) {
                    if (coll == 3) {
                        buttons[row][coll].setBackground(Color.white);
                    }
                    if (coll == 4) {
                        buttons[row][coll].setBackground(Color.black);
                    }
                }
                if (row == 4) {
                    if (coll == 3) {
                        buttons[row][coll].setBackground(Color.black);
                    }
                    if (coll == 4) {
                        buttons[row][coll].setBackground(Color.white);
                    }
                }


                button.addActionListener(this);
                super.add(button);
            }
        }
        possiblemoves();
        super.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        JButton button = (JButton) e.getSource();

        GameStatus gs = getgamestatus();


        if (gs != GameStatus.incomplete) {
            declareWinner(gs);
        }
        makemove(button);

    }

    private void declareWinner(GameStatus gs) {


        if (gs == GameStatus.black_wins) {
            JOptionPane.showMessageDialog(this, "Black wins");


            return;
        }


        if (gs == GameStatus.white_wins) {
            JOptionPane.showMessageDialog(this, "white wins");

        }

        if (gs == GameStatus.tie) {
            JOptionPane.showMessageDialog(this, "it's a tie");


        }
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart");
        if (choice == JOptionPane.YES_OPTION) {
            for (int row = 0; row < BOARD_Paths; row++) {
                for (int col = 0; col < BOARD_Paths; col++) {
                    buttons[row][col].setText("");
                }
            }
        } else {
            super.dispose();
        }

    }

    private void makemove(JButton button) {
        if (button.getBackground() != pink) {
            JOptionPane.showMessageDialog(this, "Invalid Move");
            return;

        } else { // if it is pink
            if (whiteturn == false) {
                for (int i = 0; i < BOARD_Paths; i++) {
                    for (int j = 0; j < BOARD_Paths; j++) {
                        if (buttons[i][j] == button) {
                            buttons[i][j].setBackground(black);
                            // check for horizontal rightward
                            if (j < BOARD_Paths - 1) {
                                if (buttons[i][j + 1].getBackground() == white) {
                                    int count = 0;

                                    for (int x = j + 2; x < BOARD_Paths; x++) {
                                        if (buttons[i][x].getBackground() == black) {
                                            count = count + 1;

                                        }
                                    }
                                    if (count > 0) {
                                        int k = j + 1;
                                        while (buttons[i][k].getBackground() != black && k < BOARD_Paths) {
                                            buttons[i][k].setBackground(black);
                                            k++;
                                        }
                                    }
                                }
                            }

                            // check horizontal backwards
                            if (j > 0) {
                                if (buttons[i][j - 1].getBackground() == white) {
                                    int count = 0;
                                    for (int x = j - 2; x >= 0; x--) {
                                        if (buttons[i][x].getBackground() == black) {
                                            count = count + 1;
                                        }
                                    }

                                    if (count > 0) {
                                        int k = j - 1;
                                        while (buttons[i][k].getBackground() != black && k >= 0) {
                                            buttons[i][k].setBackground(black);
                                            k--;
                                        }
                                    }
                                }
                            }
                            // check vertical downwards
                            if (i < BOARD_Paths - 1) {
                                if (buttons[i + 1][j].getBackground() == white) {
                                    int count = 0;
                                    for (int x = i + 2; x < BOARD_Paths; x++) {
                                        if (buttons[x][j].getBackground() == black) {
                                            count = count + 1;
                                        }
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        while (buttons[k][j].getBackground() != black && k < BOARD_Paths) {
                                            buttons[k][j].setBackground(black);
                                            k++;

                                        }
                                    }
                                }
                            }
                            // check vertical upwards
                            if (i > 0) {
                                if (buttons[i - 1][j].getBackground() == white) {
                                    int count = 0;
                                    for (int x = i - 2; x >= 0; x--) {
                                        if (buttons[x][j].getBackground() == black) {
                                            count = count + 1;
                                        }
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        while (buttons[k][j].getBackground() != black && k >= 0) {
                                            buttons[k][j].setBackground(black);
                                            k--;
                                        }
                                    }
                                }
                            }
                            // check right diagonal downwards
                            if (i < BOARD_Paths - 1 && j < BOARD_Paths - 1) {
                                if (buttons[i + 1][j + 1].getBackground() == white) {
                                    int count = 0;
                                    int x = i + 2;
                                    int y = j + 2;
                                    while (x < BOARD_Paths && y < BOARD_Paths) {
                                        if (buttons[x][y].getBackground() == black) {
                                            count = count + 1;

                                        }
                                        x++;
                                        y++;
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        int z = j + 1;
                                        while (buttons[k][z].getBackground() != black && k < BOARD_Paths && z < BOARD_Paths) {
                                            buttons[k][z].setBackground(black);
                                            k++;
                                            z++;

                                        }
                                    }
                                }
                            }
                            // check right diagonal upwards
                            if (i > 0 && j > 0) {
                                if (buttons[i - 1][j - 1].getBackground() == white) {
                                    int count = 0;
                                    int x = i - 2;
                                    int y = j - 2;
                                    while (x >= 0 && y >= 0) {
                                        if (buttons[x][y].getBackground() == black) {
                                            count = count + 1;
                                        }
                                        x--;
                                        y--;
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        int z = j - 1;
                                        while (buttons[k][z].getBackground() != black && k >= 0 && z >= 0) {
                                            buttons[k][z].setBackground(black);
                                            k--;
                                            z--;
                                        }
                                    }
                                }
                            }
                            // check left diagonal downwards
                            if (i < BOARD_Paths - 1 && j > 0) {
                                if (buttons[i + 1][j - 1].getBackground() == white) {
                                    int count = 0;
                                    int x = i + 2;
                                    int y = j - 2;
                                    while (x < BOARD_Paths && y >= 0) {
                                        if (buttons[x][y].getBackground() == black) {
                                            count = count + 1;
                                        }
                                        x++;
                                        y--;
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        int z = j - 1;
                                        while (buttons[k][z].getBackground() != black && k < BOARD_Paths && z >= 0) {
                                            buttons[k][z].setBackground(black);
                                            k++;
                                            z--;
                                        }
                                    }
                                }
                            }
                            // check left diagonal upwards
                            if (i > 0 && j < BOARD_Paths - 1) {
                                if (buttons[i - 1][j + 1].getBackground() == white) {
                                    int count = 0;
                                    int x = i - 2;
                                    int y = j + 2;
                                    while (x >= 0 && y < BOARD_Paths) {
                                        if (buttons[x][y].getBackground() == black) {
                                            count = count + 1;
                                        }
                                        x--;
                                        y++;
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        int z = j + 1;
                                        while (buttons[k][z].getBackground() != black && k >= 0 && z < BOARD_Paths) {
                                            buttons[k][z].setBackground(black);
                                            k--;
                                            z++;
                                        }
                                    }
                                }
                            }


                        }
                    }
                }


                for (int i = 0; i < BOARD_Paths; i++) {
                    for (int j = 0; j < BOARD_Paths; j++) {
                        if (buttons[i][j].getBackground() == pink) {
                            buttons[i][j].setBackground(green);
                        }
                    }
                }
                whiteturn = true;


            } else {
                for (int i = 0; i < BOARD_Paths; i++) {
                    for (int j = 0; j < BOARD_Paths; j++) {
                        if (buttons[i][j] == button) {
                            buttons[i][j].setBackground(white);
                            // check for horizontal rightward
                            if (j < BOARD_Paths - 1) {


                                if (buttons[i][j + 1].getBackground() == black) {
                                    // need to check whether white is present along the row
                                    int count = 0;
                                    for (int x = j + 2; x < BOARD_Paths; x++) {
                                        if (buttons[i][x].getBackground() == white) {
                                            count = count + 1;
                                        }
                                    }
                                    if (count > 0) {
                                        int k = j + 1;
                                        while (buttons[i][k].getBackground() != white && k < BOARD_Paths) {
                                            buttons[i][k].setBackground(white);
                                            k++;
                                        }
                                    }
                                }
                            }

                            // check horizontal backwards
                            if (j > 0) {
                                if (buttons[i][j - 1].getBackground() == black) {
                                    // check if white is present
                                    int count = 0;
                                    for (int x = j - 2; x >= 0; x--) {
                                        if (buttons[i][x].getBackground() == white) {
                                            count = count + 1;
                                        }
                                    }
                                    if (count > 0) {
                                        int k = j - 1;
                                        while (buttons[i][k].getBackground() != white && k >= 0) {
                                            buttons[i][k].setBackground(white);
                                            k--;
                                        }
                                    }
                                }
                            }
                            // check vertical downwards
                            if (i < BOARD_Paths - 1) {
                                if (buttons[i + 1][j].getBackground() == black) {
                                    // check if white is present
                                    int count = 0;
                                    for (int x = i + 2; x < BOARD_Paths; x++) {
                                        if (buttons[x][j].getBackground() == white) {
                                            count = count + 1;

                                        }
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        while (buttons[k][j].getBackground() != white && k < BOARD_Paths) {
                                            buttons[k][j].setBackground(white);
                                            k++;

                                        }
                                    }
                                }
                            }
                            // check vertical upwards
                            if (i > 0) {
                                if (buttons[i - 1][j].getBackground() == black) {
                                    // check if white is present
                                    int count = 0;
                                    for (int x = i - 2; x >= 0; x--) {
                                        if (buttons[x][j].getBackground() == white) {
                                            count = count + 1;
                                        }
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        while (buttons[k][j].getBackground() != white && k >= 0) {
                                            buttons[k][j].setBackground(white);
                                            k--;
                                        }
                                    }
                                }
                            }
                            // check right diagonal downwards
                            if (i < BOARD_Paths - 1 && j < BOARD_Paths - 1) {
                                if (buttons[i + 1][j + 1].getBackground() == black) {
                                    // check if white is present
                                    int count = 0;
                                    int x = i + 2;
                                    int y = j + 2;
                                    while (x < BOARD_Paths && y < BOARD_Paths) {
                                        if (buttons[x][y].getBackground() == white) {
                                            count = count + 1;
                                        }
                                        x++;
                                        y++;
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        int z = j + 1;
                                        while (buttons[k][z].getBackground() != white && k < BOARD_Paths && z < BOARD_Paths) {
                                            buttons[k][z].setBackground(white);
                                            k++;
                                            z++;

                                        }
                                    }
                                }
                            }
                            // check right diagonal upwards
                            if (i > 0 && j > 0) {
                                if (buttons[i - 1][j - 1].getBackground() == black) {
                                    int x = i - 2;
                                    int y = j - 2;
                                    int count = 0;
                                    while (x >= 0 && y >= 0) {
                                        if (buttons[x][y].getBackground() == white) {
                                            count = count + 1;
                                        }
                                        x--;
                                        y--;
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        int z = j - 1;
                                        while (buttons[k][z].getBackground() != white && k >= 0 && z >= 0) {
                                            buttons[k][z].setBackground(white);
                                            k--;
                                            z--;
                                        }
                                    }
                                }
                            }
                            // check left diagonal downwards
                            if (i < BOARD_Paths - 1 && j > 0) {
                                if (buttons[i + 1][j - 1].getBackground() == black) {
                                    int count = 0;
                                    int x = i + 2;
                                    int y = j - 2;
                                    while (x < BOARD_Paths && y >= 0) {
                                        if (buttons[x][y].getBackground() == white) {
                                            count = count + 1;

                                        }
                                        x++;
                                        y--;
                                    }
                                    if (count > 0) {
                                        int k = i + 1;
                                        int z = j - 1;
                                        while (buttons[k][z].getBackground() != white && k < BOARD_Paths && z >= 0) {
                                            buttons[k][z].setBackground(white);
                                            k++;
                                            z--;
                                        }
                                    }
                                }
                            }
                            // check left diagonal upwards
                            if (i > 0 && j < BOARD_Paths - 1) {
                                if (buttons[i - 1][j + 1].getBackground() == black) {
                                    int count = 0;
                                    int x = i - 2;
                                    int y = j + 2;
                                    while (x >= 0 && y < BOARD_Paths) {
                                        if (buttons[x][y].getBackground() == white) {
                                            count = count + 1;


                                        }
                                        x--;
                                        y++;
                                    }
                                    if (count > 0) {
                                        int k = i - 1;
                                        int z = j + 1;
                                        while (buttons[k][z].getBackground() != white && k >= 0 && z < BOARD_Paths) {
                                            buttons[k][z].setBackground(white);
                                            k--;
                                            z++;
                                        }
                                    }
                                }
                            }


                        }
                    }
                }


                for (int i = 0; i < BOARD_Paths; i++) {
                    for (int j = 0; j < BOARD_Paths; j++) {
                        if (buttons[i][j].getBackground() == pink) {
                            buttons[i][j].setBackground(green);
                        }
                    }
                }
                whiteturn = false;


            }

        }
        possiblemoves();



    }

    private void possiblemoves() {
        if (whiteturn == false) {

            for (int row = 0; row < BOARD_Paths; row++) {
                for (int coll = 0; coll < BOARD_Paths; coll++) {
                    if (buttons[row][coll].getBackground() == Color.white) {
                        // HORIZONTAL
                        if (coll > 0 && coll < BOARD_Paths - 1) {

                            if (buttons[row][coll - 1].getBackground() != Color.white) {
                                // FIRST WE WILL CHECK horizontally on right side
                                int i = coll + 1;
                                while (i < BOARD_Paths) {
                                    if (buttons[row][i].getBackground() == green || buttons[row][i].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[row][i].getBackground() == black) {
                                        buttons[row][coll - 1].setBackground(pink);// will indicate the possible moves
                                        break; // will break the loop

                                    }
                                    i++;

                                }
                            }
                            // then check horizontally on left side
                            if (buttons[row][coll + 1].getBackground() != white) {
                                int j = coll - 1;
                                while (j >= 0) {
                                    if (buttons[row][j].getBackground() == green || buttons[row][j].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[row][j].getBackground() == black) {
                                        buttons[row][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    j--;
                                }
                            }


                        }
                        // VERTICAL
                        if (row > 0 && row < BOARD_Paths - 1) {
                            if (buttons[row - 1][coll].getBackground() != white) {
                                // check vertically downwards
                                int z = row + 1;
                                while (z < BOARD_Paths) {
                                    if (buttons[z][coll].getBackground() == green || buttons[z][coll].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[z][coll].getBackground() == black) {
                                        buttons[row - 1][coll].setBackground(pink);
                                        break;
                                    }
                                    z++;
                                }
                            }
                            // check vertically upwards
                            if (buttons[row + 1][coll].getBackground() != white) {
                                int x = row - 1;
                                while (x >= 0) {
                                    if (buttons[x][coll].getBackground() == green || buttons[x][coll].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[x][coll].getBackground() == black) {
                                        buttons[row + 1][coll].setBackground(pink);
                                        break;

                                    }
                                    x--;
                                }
                            }

                        }
                        // right side diagonal
                        if (row > 0 && row < BOARD_Paths - 1 && coll > 0 && coll < BOARD_Paths - 1) {
                            if (buttons[row - 1][coll - 1].getBackground() != white) {
                                // check downwards
                                int k = row + 1;
                                int z = coll + 1;
                                while (k < BOARD_Paths && z < BOARD_Paths) {
                                    if (buttons[k][z].getBackground() == green || buttons[k][z].getBackground() == pink) {
                                        break;
                                    }

                                    if (buttons[k][z].getBackground() == black) {
                                        buttons[row - 1][coll - 1].setBackground(pink);
                                        break;
                                    }
                                    k = k + 1;

                                    z = z + 1;

                                }
                            }
                            // check upwards
                            if (buttons[row + 1][coll + 1].getBackground() != white) {
                                int x = row - 1;
                                int i = coll - 1;
                                while (x >= 0 && i >= 0) {

                                    if (buttons[x][i].getBackground() == green || buttons[x][i].getBackground() == pink) {
                                        break;
                                    }


                                    if (buttons[x][i].getBackground() == black) {
                                        buttons[row + 1][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    x--;
                                    i--;
                                }
                            }


                        }
                        // left side diagonal
                        if (row > 0 && row < BOARD_Paths - 1 && coll > 0 && coll < BOARD_Paths - 1) {
                            if (buttons[row - 1][coll + 1].getBackground() != white) {
                                // check downwards
                                int i = row + 1;
                                int j = coll - 1;
                                while (i < BOARD_Paths && j >= 0) {

                                    if (buttons[i][j].getBackground() == green || buttons[i][j].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[i][j].getBackground() == black) {
                                        buttons[row - 1][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    i++;
                                    j--;
                                }
                            }
                            // check upwards
                            if (buttons[row + 1][coll - 1].getBackground() != white) {
                                int x = row - 1;
                                int k = coll + 1;
                                while (x >= 0 && k < BOARD_Paths) {

                                    if (buttons[x][k].getBackground() == green || buttons[x][k].getBackground() == pink) {
                                        break;
                                    }


                                    if (buttons[x][k].getBackground() == black) {
                                        buttons[row + 1][coll - 1].setBackground(pink);
                                        break;
                                    }
                                    x--;
                                    k++;

                                }
                            }

                        }
                    }
                }
            }
            return;


        } else {
            for (int row = 0; row < BOARD_Paths; row++) {
                for (int coll = 0; coll < BOARD_Paths; coll++) {
                    if (buttons[row][coll].getBackground() == black) {
                        // HORIZONTAL
                        if (coll > 0 && coll < BOARD_Paths - 1) {

                            if (buttons[row][coll - 1].getBackground() != black) {
                                // FIRST WE WILL CHECK horizontally on right side
                                int i = coll + 1;
                                while (i < BOARD_Paths) {

                                    if (buttons[row][i].getBackground() == green || buttons[row][i].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[row][i].getBackground() == white) {
                                        buttons[row][coll - 1].setBackground(pink);// will indicate the possible moves
                                        break; // will break the loop

                                    }
                                    i++;

                                }
                            }

                            // then check horizontally on left side
                            if (buttons[row][coll + 1].getBackground() != black) {
                                int j = coll - 1;
                                while (j >= 0) {
                                    if (buttons[row][j].getBackground() == green || buttons[row][j].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[row][j].getBackground() == white) {
                                        buttons[row][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    j--;
                                }
                            }


                        }
                        // VERTICAl
                        if (row > 0 && row < BOARD_Paths - 1) {
                            if (buttons[row - 1][coll].getBackground() != black) {
                                // check vertically downwards
                                int z = row + 1;
                                while (z < BOARD_Paths) {
                                    if (buttons[z][coll].getBackground() == green || buttons[z][coll].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[z][coll].getBackground() == white) {
                                        buttons[row - 1][coll].setBackground(pink);
                                        break;
                                    }
                                    z++;
                                }
                            }
                            // check verticlly upwards
                            if (buttons[row + 1][coll].getBackground() != black) {
                                int x = row - 1;
                                while (x >= 0) {
                                    if (buttons[x][coll].getBackground() == green || buttons[x][coll].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[x][coll].getBackground() == white) {
                                        buttons[row + 1][coll].setBackground(pink);
                                        break;

                                    }
                                    x--;
                                }
                            }

                        }
                        // right side diagonal
                        if (row > 0 && row < BOARD_Paths - 1 && coll > 0 && coll < BOARD_Paths - 1) {
                            if (buttons[row - 1][coll - 1].getBackground() != black) {
                                // check downwards
                                int k = row + 1;
                                int z = coll + 1;
                                while (k < BOARD_Paths && z < BOARD_Paths) {
                                    if (buttons[k][z].getBackground() == green || buttons[k][z].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[k][z].getBackground() == white) {
                                        buttons[row - 1][coll - 1].setBackground(pink);
                                        break;
                                    }
                                    k++;
                                    z++;

                                }
                            }
                            if (buttons[row + 1][coll + 1].getBackground() != black) {
                                int x = row - 1;
                                int i = coll - 1;
                                while (x >= 0 && i >= 0) {
                                    if (buttons[x][i].getBackground() == green || buttons[x][i].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[x][i].getBackground() == white) {
                                        buttons[row + 1][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    x--;
                                    i--;
                                }
                            }
                        }


                        // left side diagonal
                        if (row > 0 && row < BOARD_Paths - 1 && coll > 0 && coll < BOARD_Paths - 1) {


                            if (buttons[row - 1][coll + 1].getBackground() != black) {
                                // check downwards
                                int i = row + 1;
                                int j = coll - 1;
                                while (i < BOARD_Paths && j >= 0) {
                                    if (buttons[i][j].getBackground() == green || buttons[i][j].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[i][j].getBackground() == white) {
                                        buttons[row - 1][coll + 1].setBackground(pink);
                                        break;
                                    }
                                    i++;
                                    j--;
                                }
                            }
                            // check upwards
                            if (buttons[row + 1][coll - 1].getBackground() != black) {
                                int x = row - 1;
                                int k = coll + 1;
                                while (x >= 0 && k < BOARD_Paths) {
                                    if (buttons[x][k].getBackground() == green || buttons[x][k].getBackground() == pink) {
                                        break;
                                    }
                                    if (buttons[x][k].getBackground() == white) {
                                        buttons[row + 1][coll - 1].setBackground(pink);
                                        break;
                                    }
                                    x--;
                                    k++;

                                }
                            }

                        }
                    }
                }
            }


        }
    }

    private GameStatus getgamestatus() {
        int count = 0;
        for (int row = 0; row < BOARD_Paths; row++) {
            for (int coll = 0; coll < BOARD_Paths; coll++) {
                if (buttons[row][coll].getBackground() == pink) {
                    count = count + 1;
                }

            }
        }
        if (count > 0) {
            return GameStatus.incomplete;
        } else { // count ==0
            if(whiteturn==true){
                whiteturn=false;
                possiblemoves();
                int pinkcount =0;
                for(int row=0;row<BOARD_Paths;row++){
                    for(int coll=0;coll<BOARD_Paths;coll++){
                        if(buttons[row][coll].getBackground()==pink){
                            pinkcount = pinkcount +1;
                        }
                    }
                }
                if(pinkcount==0){
                    // this means that game is finished
                    int blackcount =0;
                    int whitecount =0;
                    for(int i=0;i<BOARD_Paths;i++){
                        for(int j=0;j<BOARD_Paths;j++){
                            if(buttons[i][j].getBackground()==white){
                                whitecount = whitecount+1;
                            }
                            if(buttons[i][j].getBackground()==black){
                                blackcount= blackcount+1;
                            }
                        }
                    }
                    if(blackcount>whitecount){
                        return GameStatus.black_wins;
                    }else{
                        return GameStatus.white_wins;
                    }

                }else{
                    return GameStatus.incomplete;
                }


            }else{
                whiteturn=true;
                possiblemoves();
                int pinkcount =0;
                for(int row=0;row<BOARD_Paths;row++){
                    for(int coll=0;coll<BOARD_Paths;coll++){
                        if(buttons[row][coll].getBackground()==pink){
                            pinkcount = pinkcount +1;
                        }
                    }
                }
                if(pinkcount==0){
                    // this means that game is finished
                    int blackcount =0;
                    int whitecount =0;
                    for(int i=0;i<BOARD_Paths;i++){
                        for(int j=0;j<BOARD_Paths;j++){
                            if(buttons[i][j].getBackground()==white){
                                whitecount = whitecount+1;
                            }
                            if(buttons[i][j].getBackground()==black){
                                blackcount= blackcount+1;
                            }
                        }
                    }
                    if(blackcount>whitecount){
                        return GameStatus.black_wins;
                    }else{
                        return GameStatus.white_wins;
                    }

                }else{
                    return GameStatus.incomplete;
                }
            }



        }
    }
}

