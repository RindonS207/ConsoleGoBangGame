package rindong.project;

import java.util.Scanner;

public class Main {
    //棋盘
    public static String[][] GoBang = new String[15][15];
    public static boolean isBlackMove = true;
    public static int piece_count = 0;
    public static Scanner inputManager = new Scanner(System.in);

    public static final String BLACKPIECE = "●";
    public static final String WHITEPIECE = "○";
    public static final String CLEARPIECE = "+";

    public static void main(String[] args)
    {
        initGobang();
        gameLoop();
    }

    public static void initGobang()
    {
        for (int x = 0;x<15;x++)
        {
            for (int i = 0;i<15;i++)
            {
                GoBang[x][i] = CLEARPIECE;
            }
        }
    }

    public static void printGobang()
    {
        System.out.print("\t");
        for (int x = 1;x<=15;x++)
        {
            System.out.print(x + "\t");
        }
        System.out.println();
        for (int x = 1;x<=15;x++)
        {
            System.out.print(x + "\t");
            for (int i = 1;i<=15;i++)
            {
                System.out.print(GoBang[x-1][i-1] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean playChess(int x,int y)
    {
        if (piece_count == 0)
        {
            if (x != 8 || y != 8)
            {
                System.out.println("第一枚棋子必须下在中央！");
                return false;
            }
            else
            {
                GoBang[7][7] = BLACKPIECE;
                isBlackMove = !isBlackMove;
                piece_count += 1;
                return true;
            }
        }
        if (isBlackMove)
        {
            if (!GoBang[x-1][y-1].equals(CLEARPIECE))
            {
                System.out.println("这个地方不是空位！");
                return false;
            }
            else
            {
                GoBang[x-1][y-1] = BLACKPIECE;
                isBlackMove = !isBlackMove;
                piece_count += 1;
                return true;
            }
        }
        else
        {
            if (!GoBang[x-1][y-1].equals(CLEARPIECE))
            {
                System.out.println("这个地方不是空位！");
                return false;
            }
            else
            {
                GoBang[x-1][y-1] = WHITEPIECE;
                isBlackMove = !isBlackMove;
                piece_count += 1;
                return true;
            }
        }
    }

    public static boolean isWin(int x,int y)
    {
        int count = 1;
        int findCount = 1;
        int tempX = x;
        int tempY = y;
        final String PIECE = GoBang[x][y];
        //左切角
        while (true)
        {
            for (int value = 1;value<=4;value++)
            {
                if ((tempX - value != -1) && (tempY - value != -1))
                {
                    if (GoBang[tempX - value][tempY - value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count += ((4 - value + 1));
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            for (int value = 1;value<=4;value++)
            {
                if ((tempX + value != 15) && (tempY + value != 15))
                {
                    if (GoBang[tempX + value][tempY + value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count += ((4 - value + 1));
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            if (count == 9)
            {
                findCount = 1;
                break;
            }
        }

        //右切角
        while (true)
        {
            for (int value = 1;value<=4;value++)
            {
                if ((tempX + value != 15) && (tempY - value != -1))
                {
                    if (GoBang[tempX + value][tempY - value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count += ((4 - value + 1));
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            for (int value = 1;value<=4;value++)
            {
                if ((tempX - value != -1) && (tempY + value != 15))
                {
                    if (GoBang[tempX - value][tempY + value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count += ((4 - value + 1));
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            if (count == 17)
            {
                findCount = 1;
                break;
            }
        }

        //上下
        while (true)
        {
            for (int value = 1;value<=4;value++)
            {
                if ((tempY - value != -1))
                {
                    if (GoBang[tempX][tempY - value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count +=((4 - value + 1));
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            for (int value = 1;value<=4;value++)
            {
                if ((tempY + value != 15))
                {
                    if (GoBang[tempX][tempY + value].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += (4 - value + 1);
                        break;
                    }
                }
                else
                {
                    count += (4 - value + 1);
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            if (count == 25)
            {
                findCount = 1;
                break;
            }
        }

        //左右
        while (true)
        {
            for (int value = 1;value<=4;value++)
            {
                if ((tempX - value != -1))
                {
                    if (GoBang[tempX - value][tempY].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += (4 - value + 1);
                        break;
                    }
                }
                else
                {
                    count += (4 - value + 1);
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            for (int value = 1;value<=4;value++)
            {
                if ((tempX + value != 15))
                {
                    if (GoBang[tempX + value][tempY].equals(PIECE))
                    {
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        count += (4 - value + 1);
                        break;
                    }
                }
                else
                {
                    count += (4 - value + 1);
                    break;
                }
                if (findCount >= 5)
                {
                    return true;
                }
            }
            if (count == 33)
            {
                findCount = 1;
                break;
            }
        }
        return false;
    }

    public static void gameLoop()
    {
        int inputX = 0;
        int inputY = 0;
        do {
            printGobang();
            if (piece_count == 225)
            {
                System.out.println("棋子已经下完。。。没有赢家。");
                break;
            }
            System.out.println("现在是:" + (isBlackMove ? "黑色方行动" : "白色方行动"));
            System.out.println("请先输入想放置的行（X）：");
            inputX = inputManager.nextInt();
            System.out.println("请输入想放置的列（Y）：");
            inputY = inputManager.nextInt();
            if (!playChess(inputY,inputX))
            {
                gameLoop();
            }
        }
        while (!isWin(inputY-1,inputX-1));
        printGobang();
        if (!isBlackMove && piece_count < 255)
        {
            System.out.println("恭喜黑色方获胜！");
        }
        else if (piece_count < 255)
        {
            System.out.println("恭喜白色方获胜！");
        }
    }
}
