/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackbox;

import java.util.Scanner;

/**
 *
 * @author 
 */
public class BlackBox {
    
    /**
     * @param args the command line arguments
     */
    public static void init(int[][] _atoms) {
        for (int i = 0; i < _atoms.length; i++)
            for (int j = 0; j < _atoms[i].length; j++)
                _atoms[i][j] = 0;
    }
    
    public static void whereAtoms(int[][] _atoms) {
        int[] nums = new int [2];
        int j=1;
       
        while(j<5) {
            Scanner in = new Scanner (System.in) ;
            System.out.print("Where is the Atom #" + j + " ? Row:[1-8] Column:[1-8] ? ");

            if (in.hasNextInt()) {
                nums[0] = in.nextInt();
            } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
            }
            if (in.hasNextInt()) {
                nums[1] = in.nextInt();
            } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
            }
            
            if (!(nums[0] >= 1 && nums[1] >= 1 && nums[0] <= 8 && nums[1] <= 8)) {
                System.out.println("Error, invalid input, please try again!");
                continue;
            }
            
             if (_atoms[nums[1]][nums[0]] != 1) {
                _atoms[nums[1]][nums[0]] = 1;
                j++;
             } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
             }
        }// end of while
    }  
      
    public static void printMap(int[][] _atoms) {
        System.out.print("         *TOP*\n");
        System.out.print("     1 2 3 4 5 6 7 8\n");
        
        for (int i = 1; i < 9; i++) {
            switch(i) {
                case 1:
                    System.out.print("   1 ");
                    break;
                case 2:
                    System.out.print(" * 2 ");
                    break;    
                case 3:
                    System.out.print(" L 3 ");
                    break;
                case 4:
                    System.out.print(" E 4 ");
                    break;  
                case 5:
                    System.out.print(" F 5 ");
                    break;
                case 6:
                    System.out.print(" T 6 ");
                    break;  
                case 7:
                    System.out.print(" * 7 ");
                    break;
                case 8:
                    System.out.print("   8 ");
                    break;
            }
            
            for (int j = 1; j < 9; j++) {
                if (_atoms[j][i] == 1)
                    System.out.print("@" + " ");
                else
                    System.out.print("." + " ");
            }
            
            switch(i) {
                case 1:
                    System.out.print("1 ");
                    break;
                case 2:
                    System.out.print("2 *");
                    break;    
                case 3:
                    System.out.print("3 R");
                    break;
                case 4:
                    System.out.print("4 I");
                    break;  
                case 5:
                    System.out.print("5 G");
                    break;
                case 6:
                    System.out.print("6 H");
                    break;  
                case 7:
                    System.out.print("7 T");
                    break;
                case 8:
                    System.out.print("8 *");
                    break;
            }
            
            System.out.println();
       }
        System.out.print("     1 2 3 4 5 6 7 8\n");
        System.out.print("        *BOTTOM*\n");
         
    }

    
    public static void main(String[] args) {
        int[][] atoms = new int[15][15]; //container: 8x8
        int rayStartSide = 0;
        int rayStartPos = 0;
        int rayDir = 0;
        int rayX=0;
        int rayY=0;
        int num =0;
        Scanner in = new Scanner (System.in) ;

        init(atoms);
        whereAtoms(atoms);
        
        while(true) {
        boolean first = true;
        printMap(atoms);
        //askRayStartSide(rayStartSide);
         while(true) {
            in = new Scanner (System.in) ;
            System.out.print("What is the ray position ? [1=TOP, 2=BOTTOM, 3=LEFT, 4=RIGHT, -1=EXIT] ? ");

            if (in.hasNextInt()) {
                num = in.nextInt();
                
                if (num > 0 && num <5) {
                    rayStartSide = num;
                    break;
                }
                else if (num == -1) {
                    System.out.print("Bye!");
                    System.exit(0);
                } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
                }
             
            } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
            }
         }
        
        //askRayStartPos(rayStartPos);  
         while(true){
            in = new Scanner (System.in) ;
            System.out.print("Where should the ray start ? [1-8] ? ");

            if (in.hasNextInt()) {
                num = in.nextInt();
                
                if (num > 0 && num <9) {
                    rayStartPos = num;
                    break;
                } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
                }
             
            } else {
                System.out.println("Error, invalid input, please try again!");
                continue;
            }
         }

        //rayMoving   
        switch(rayStartSide) {
            case 1:
                rayDir = 2;
                rayY = 0;
                rayX = rayStartPos;
                break;
            case 2:
                rayDir = 1;
                rayY = 9;
                rayX = rayStartPos;
                break;
            case 3:
                rayDir = 4;
                rayX = 0;
                rayY = rayStartPos;
                break;
            case 4:
                rayDir = 3;
                rayX = 9;
                rayY = rayStartPos;
                break;
        } 
        
        int rayStartX = rayX;
        int rayStartY = rayY;
        //1st move check hit
        
         switch(rayDir) {
            case 1:
                rayY--;
                break;
            case 2:
               rayY++;
                break;
            case 3:
                rayX--;
                break;
            case 4:
                rayX++;
                break;
        } 
         
         
        while(true) {
           if (atoms[rayX][rayY] == 1) { //hit
                System.out.print("--> Ray Absorbed\n");
                System.out.print("Terminating Position: Not Available\n");
                System.out.print("Outcome = Hit\n");
                break;
         } else {
                System.out.print("Ray at Row:"+rayY+",Col:"+rayX+"\n");
               
               if ( rayX == 1 || rayX == 8 || rayY == 1 || rayY == 8 ) { //arrived exit
             
                    if (first) {
                            System.out.print("Staring Position: ");
        
                            switch(rayStartSide) {
                                case 1: 
                                    System.out.print("TOP");
                                    break;
                                case 2: 
                                    System.out.print("BOTTOM");
                                    break;
                                case 3: 
                                    System.out.print("LEFT");
                                    break;
                                case 4: 
                                    System.out.print("RIGHT");
                                    break;
                            }
        
                            System.out.print(" " + rayStartPos);
                            System.out.println();
                            first = false;
                    } else {
                    System.out.print("Terminating Position: ");
                            
                    switch(rayDir) {
                        case 1: 
                            System.out.print("TOP ");
                            System.out.print(rayX);
                            break;
                        case 2: 
                            System.out.print("BOTTOM ");
                            System.out.print(rayX);
                            break;
                        case 3: 
                            System.out.print("LEFT ");
                            System.out.print(rayY);
                            break;
                        case 4: 
                            System.out.print("RIGHT ");
                            System.out.print(rayY);
                            break;
                    }
                    System.out.println();
                    
                    //outcome
                    if (rayX == rayStartX && rayY == rayStartY) {
                        System.out.print("Outcome = Reflection\n");
                        break;
                    } else if (rayX == rayStartX && ((rayY ==9 && rayStartY ==1) || (rayY ==1 && rayStartY ==9)|| (rayY ==8 && rayStartY ==0)|| (rayY ==0 && rayStartY ==8))) {
                        System.out.print("Outcome = Miss\n");
                        break;
                    } else if (rayY == rayStartY && ((rayX ==9 && rayStartX ==1) || (rayX ==1 && rayStartX ==9)|| (rayX ==8 && rayStartX ==0)|| (rayX ==0 && rayStartX ==8))) {
                        System.out.print("Outcome = Miss\n");
                        break;
                    } else {
                        System.out.print("Outcome = Detour\n");
                        break;
                    }
                    }  
         }
           }
           
         
        //check situation
        if (( rayX == 0 || rayX == 8 ) && (atoms[rayX][rayY+1] == 1 || atoms[rayX][rayY-1] == 1)) { //reflected
            if (rayDir ==3)
                rayDir =4;
            else
                rayDir =3;
            System.out.print("--> Ray Reflected\n");
        } else if (( rayY == 0 || rayY == 8 ) && (atoms[rayX+1][rayY] == 1 || atoms[rayX-1][rayY] == 1)) { //reflected
            if (rayDir ==1)
                rayDir =2;
            else
                rayDir =1;
            System.out.print("--> Ray Reflected\n");
        } else if (rayDir ==1 && atoms[rayX-1][rayY-1] == 1 && atoms[rayX+1][rayY-1] == 1 ) { //reflected
            rayDir =2;
            System.out.print("--> Ray Reflected\n");
        } else if (rayDir ==2 && atoms[rayX-1][rayY+1] == 1 && atoms[rayX+1][rayY+1] == 1 ) { //reflected
            rayDir =1;
            System.out.print("--> Ray Reflected\n");
        } else if (rayDir ==3 && atoms[rayX-1][rayY-1] == 1 && atoms[rayX-1][rayY+1] == 1 ) { //reflected
            rayDir =4;
            System.out.print("--> Ray Reflected\n");
        } else if (rayDir ==4 && atoms[rayX+1][rayY-1] == 1 && atoms[rayX+1][rayY+1] == 1 ) { //reflected
            rayDir =3;
            System.out.print("--> Ray Reflected\n");
        } else if (rayDir == 1 && atoms[rayX-1][rayY-1] == 1) {//right
            rayDir = 4;
            rayX++;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 1 && atoms[rayX+1][rayY-1] == 1) {//left
            rayDir = 3;
            rayX--;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 2 && atoms[rayX-1][rayY+1] == 1) {//right
            rayDir = 4;
            rayX++;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 2 && atoms[rayX+1][rayY+1] == 1) {//left
            rayDir = 3;
            rayX--;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 3 && atoms[rayX-1][rayY+1] == 1) {//up
            rayDir = 1;
            rayY--;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 3 && atoms[rayX-1][rayY-1] == 1) {//down
            rayDir = 2;
            rayY++;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 4 && atoms[rayX+1][rayY+1] == 1) {//up
            rayDir = 1;
            rayY--;
            System.out.print("--> Ray Deflected\n");
        } else if (rayDir == 4 && atoms[rayX+1][rayY-1] == 1) {//down
            rayDir = 2;
            rayY++;
            System.out.print("--> Ray Deflected\n");
        } else {
            switch(rayDir) {
                case 1:
                    rayY--;
                    break;
                case 2:
                   rayY++;
                    break;
                case 3:
                    rayX--;
                    break;
                case 4:
                    rayX++;
                    break;
            } 
        }
        
        
        }//end of moving while
    }
    }
}