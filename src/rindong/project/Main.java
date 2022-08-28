package rindong.project;

import java.util.Scanner;

public class Main {
    //棋盘
    public static String[][] GoBang = new String[15][15];
    //判断是不是黑色方移动
    public static boolean isBlackMove = true;
    //整个棋盘的棋子数量
    public static int piece_count = 0;
    //用于接收玩家输入坐标
    public static Scanner inputManager = new Scanner(System.in);
 
    //黑色棋子
    public static final String BLACKPIECE = "●";
    //白色棋子
    public static final String WHITEPIECE = "○";
    //空位
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
        //用于记录已经判断过的棋子数量
        int count = 1;
        int findCount = 1;
        int tempX = x;
        int tempY = y;
        final String PIECE = GoBang[x][y];
        //左切角
        while (true)
        {
            //最多的情况一个方向有四个或以上的相连
            for (int value = 1;value<=4;value++)
            {
                //防止超出数组的索引
                if ((tempX - value != -1) && (tempY - value != -1))
                {
                    //如果是同类型的棋子
                    if (GoBang[tempX - value][tempY - value].equals(PIECE))
                    {
                        //相连数+1，已经进行判断的数量+1
                        findCount += 1;
                        count += 1;
                    }
                    else
                    {
                        //如果不是同类型棋子则代表被不同颜色的棋子或空位截断，把被截断的数量添加到已判断的棋子数量.
                        count += ((4 - value + 1));
                        break;
                    }
                }
                else
                {
                    count += ((4 - value + 1));
                    break;
                }
                //如果这个方向已经有5个棋子，返回true，游戏胜利。
                if (findCount >= 5)
                {
                    return true;
                }
            }
            //左切角的另一个方向，原理相同不再赘述。
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
        //否则游戏继续
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
