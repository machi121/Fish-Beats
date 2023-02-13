import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.*;

public class Fish_and_Beats extends JPanel implements MouseListener, KeyListener, Runnable {
	
	// Game States
	// GS0 - Menu screen
	// GS1 - In-game screen
	// GS2 - About
	// GS3 - Credits
	// GS4 - Track 1
	// GS5 - Track 2
	// GS6 - Track 3

	public static int gameState = 0;
	
	// Mouse Press Coordinates
	public static int posX, posY;
	
	// Music
	public static AudioInputStream ais;
	public static Clip clip;
	
	// Backgrounds for game states
	public static BufferedImage menu, bg, about, credits, highScores, track1, track2, track3;
	
	// Scores
	public static int score = 0;
	public static int highscore1 = 0;
	public static int highscore2 = 0;
	public static int highscore3 = 0; 
	
	// Notes
	public static String noteJudgmentText = " ";
	public static boolean missed = true;

	// Track stats
	public static int trackNo; 
	
	// General Game Stats
	public static int animationFrameCounter = 0;
	public static int frameCounter = 0;
	
	// Cold Shark Game Stats
	public static BufferedImage cShark;
	public static int cSharkX = 650;
	public static int cSharkY = 100;
	
	// Dark Shark Game Stats
	public static BufferedImage dShark;
	public static int dSharkX = 660;
	public static int dSharkY = 400;
	
	// Fish Game Stats
	public static BufferedImage[] fish = new BufferedImage[8];
	public static int fishAnimationNum = 0;
	
	// Row: fish index
	// Column: X-coordinate [0], Y-coordinate [1]
	public static int[][] fishCoordinates = new int[1000][2]; 
	
	public Fish_and_Beats() {
		setPreferredSize(new Dimension(800, 600));
		// Add MouseListener
		addMouseListener(this);
		// Add KeyListener
		addKeyListener(this);
		this.setFocusable(true);
		// Add Timer
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gameState == 0) {
			resetGame();
			g.drawImage(menu, 0, 0, null);
		}
		else if(gameState == 1) {
			frameCounter++;
			g.drawImage(bg, 0, 0, null);
			g.setColor(new Color(31, 19, 71));
			g.drawImage(cShark, cSharkX, cSharkY, null);
			g.drawImage(dShark, dSharkX, dSharkY, null);
			g.setFont(new Font("Handy Casual", Font.PLAIN, 20));
			// Draw the score
			g.drawString(String.valueOf(score), 400, 570);
			// Draw the note judgment text
			g.drawString(noteJudgmentText, 390, 30);
			updateAnimation();
			
			// Fish generation
			
			if(trackNo == 1) {
				// Draw the high score
				g.drawString(String.valueOf(highscore1), 190, 40);
				
				/* for(int i = 0; i < fishCoordinates.length; i++) {
					System.out.println("if(frameCounter >= " + i + ") {");
					System.out.println("    g.drawImage(fish[fishAnimationNum], getX(" + i + "), getY(" + i + "), null);");
					System.out.println("    moveFish(" + i + ");");
					System.out.println("}");
				} */
				
				if(frameCounter == 48) {
					playMusic();
				}
				
				if(frameCounter >= 0) {
				    g.drawImage(fish[fishAnimationNum], getX(0), getY(0), null);
				    moveFish(0);
				}
				
				if(frameCounter >= 60) {
				    g.drawImage(fish[fishAnimationNum], getX(1), getY(1), null);
				    moveFish(1);
				}
				if(frameCounter >= 123) {
				    g.drawImage(fish[fishAnimationNum], getX(2), getY(2), null);
				    moveFish(2);
				}
				if(frameCounter >= 189) {
				    g.drawImage(fish[fishAnimationNum], getX(3), getY(3), null);
				    moveFish(3);
				}
				if(frameCounter >= 253) {
				    g.drawImage(fish[fishAnimationNum], getX(4), getY(4), null);
				    moveFish(4);
				}
				if(frameCounter >= 284) {
				    g.drawImage(fish[fishAnimationNum], getX(5), getY(5), null);
				    moveFish(5);
				}
				if(frameCounter >= 381) {
				    g.drawImage(fish[fishAnimationNum], getX(6), getY(6), null);
				    moveFish(6);
				}
				if(frameCounter >= 412) {
				    g.drawImage(fish[fishAnimationNum], getX(7), getY(7), null);
				    moveFish(7);
				}
				if(frameCounter >= 509) {
				    g.drawImage(fish[fishAnimationNum], getX(8), getY(8), null);
				    moveFish(8);
				}
				if(frameCounter >= 540) {
				    g.drawImage(fish[fishAnimationNum], getX(9), getY(9), null);
				    moveFish(9);
				}
				if(frameCounter >= 637) {
				    g.drawImage(fish[fishAnimationNum], getX(10), getY(10), null);
				    moveFish(10);
				}
				if(frameCounter >= 671) {
				    g.drawImage(fish[fishAnimationNum], getX(11), getY(11), null);
				    moveFish(11);
				}
				if(frameCounter >= 767) {
				    g.drawImage(fish[fishAnimationNum], getX(12), getY(12), null);
				    moveFish(12);
				}
				if(frameCounter >= 798) {
				    g.drawImage(fish[fishAnimationNum], getX(13), getY(13), null);
				    moveFish(13);
				}
				if(frameCounter >= 830) {
				    g.drawImage(fish[fishAnimationNum], getX(14), getY(14), null);
				    moveFish(14);
				}
				if(frameCounter >= 863) {
				    g.drawImage(fish[fishAnimationNum], getX(15), getY(15), null);
				    moveFish(15);
				}
				if(frameCounter >= 894) {
				    g.drawImage(fish[fishAnimationNum], getX(16), getY(16), null);
				    moveFish(16);
				}
				if(frameCounter >= 926) {
				    g.drawImage(fish[fishAnimationNum], getX(17), getY(17), null);
				    moveFish(17);
				}
				if(frameCounter >= 957) {
				    g.drawImage(fish[fishAnimationNum], getX(18), getY(18), null);
				    moveFish(18);
				}
				if(frameCounter >= 990) {
				    g.drawImage(fish[fishAnimationNum], getX(19), getY(19), null);
				    moveFish(19);
				}
				if(frameCounter >= 1022) {
				    g.drawImage(fish[fishAnimationNum], getX(20), getY(20), null);
				    moveFish(20);
				}
				if(frameCounter >= 1030) {
				    g.drawImage(fish[fishAnimationNum], getX(21), getY(21), null);
				    moveFish(21);
				}
				if(frameCounter >= 1040) {
				    g.drawImage(fish[fishAnimationNum], getX(22), getY(22), null);
				    moveFish(22);
				}
				if(frameCounter >= 1049) {
				    g.drawImage(fish[fishAnimationNum], getX(23), getY(23), null);
				    moveFish(23);
				}
				if(frameCounter >= 1066) {
				    g.drawImage(fish[fishAnimationNum], getX(24), getY(24), null);
				    moveFish(24);
				}
				if(frameCounter >= 1087) {
				    g.drawImage(fish[fishAnimationNum], getX(25), getY(25), null);
				    moveFish(25);
				}
				if(frameCounter >= 1096) {
				    g.drawImage(fish[fishAnimationNum], getX(26), getY(26), null);
				    moveFish(26);
				}
				if(frameCounter >= 1104) {
				    g.drawImage(fish[fishAnimationNum], getX(27), getY(27), null);
				    moveFish(27);
				}
				if(frameCounter >= 1112) {
				    g.drawImage(fish[fishAnimationNum], getX(28), getY(28), null);
				    moveFish(28);
				}
				if(frameCounter >= 1127) {
				    g.drawImage(fish[fishAnimationNum], getX(29), getY(29), null);
				    moveFish(29);
				}
				if(frameCounter >= 1150) {
				    g.drawImage(fish[fishAnimationNum], getX(30), getY(30), null);
				    moveFish(30);
				}
				if(frameCounter >= 1164) {
				    g.drawImage(fish[fishAnimationNum], getX(31), getY(31), null);
				    moveFish(31);
				}
				if(frameCounter >= 1181) {
				    g.drawImage(fish[fishAnimationNum], getX(32), getY(32), null);
				    moveFish(32);
				}
				if(frameCounter >= 1214) {
				    g.drawImage(fish[fishAnimationNum], getX(33), getY(33), null);
				    moveFish(33);
				}
				if(frameCounter >= 1238) {
				    g.drawImage(fish[fishAnimationNum], getX(34), getY(34), null);
				    moveFish(34);
				}
				if(frameCounter >= 1277) {
				    g.drawImage(fish[fishAnimationNum], getX(35), getY(35), null);
				    moveFish(35);
				}
				if(frameCounter >= 1305) {
				    g.drawImage(fish[fishAnimationNum], getX(36), getY(36), null);
				    moveFish(36);
				}
				if(frameCounter >= 1340) {
				    g.drawImage(fish[fishAnimationNum], getX(37), getY(37), null);
				    moveFish(37);
				}
				if(frameCounter >= 1366) {
				    g.drawImage(fish[fishAnimationNum], getX(38), getY(38), null);
				    moveFish(38);
				}
				if(frameCounter >= 1408) {
				    g.drawImage(fish[fishAnimationNum], getX(39), getY(39), null);
				    moveFish(39);
				}
				if(frameCounter >= 1435) {
				    g.drawImage(fish[fishAnimationNum], getX(40), getY(40), null);
				    moveFish(40);
				}
				if(frameCounter >= 1467) {
				    g.drawImage(fish[fishAnimationNum], getX(41), getY(41), null);
				    moveFish(41);
				}
				if(frameCounter >= 1498) {
				    g.drawImage(fish[fishAnimationNum], getX(42), getY(42), null);
				    moveFish(42);
				}
				if(frameCounter >= 1533) {
				    g.drawImage(fish[fishAnimationNum], getX(43), getY(43), null);
				    moveFish(43);
				}
				if(frameCounter >= 1563) {
				    g.drawImage(fish[fishAnimationNum], getX(44), getY(44), null);
				    moveFish(44);
				}
				if(frameCounter >= 1595) {
				    g.drawImage(fish[fishAnimationNum], getX(45), getY(45), null);
				    moveFish(45);
				}
				if(frameCounter >= 1628) {
				    g.drawImage(fish[fishAnimationNum], getX(46), getY(46), null);
				    moveFish(46);
				}
				if(frameCounter >= 1661) {
				    g.drawImage(fish[fishAnimationNum], getX(47), getY(47), null);
				    moveFish(47);
				}
				if(frameCounter >= 1691) {
				    g.drawImage(fish[fishAnimationNum], getX(48), getY(48), null);
				    moveFish(48);
				}
				if(frameCounter >= 1722) {
				    g.drawImage(fish[fishAnimationNum], getX(49), getY(49), null);
				    moveFish(49);
				}
				if(frameCounter >= 1755) {
				    g.drawImage(fish[fishAnimationNum], getX(50), getY(0), null);
				    moveFish(50);
				}
				if(frameCounter >= 1786) {
				    g.drawImage(fish[fishAnimationNum], getX(51), getY(1), null);
				    moveFish(51);
				}
				if(frameCounter >= 1818) {
				    g.drawImage(fish[fishAnimationNum], getX(52), getY(2), null);
				    moveFish(52);
				}
				if(frameCounter >= 1852) {
				    g.drawImage(fish[fishAnimationNum], getX(53), getY(3), null);
				    moveFish(53);
				}
				if(frameCounter >= 1885) {
				    g.drawImage(fish[fishAnimationNum], getX(54), getY(4), null);
				    moveFish(54);
				}
				if(frameCounter >= 1916) {
				    g.drawImage(fish[fishAnimationNum], getX(55), getY(5), null);
				    moveFish(55);
				}
				if(frameCounter >= 1948) {
				    g.drawImage(fish[fishAnimationNum], getX(56), getY(6), null);
				    moveFish(56);
				}
				if(frameCounter >= 1982) {
				    g.drawImage(fish[fishAnimationNum], getX(57), getY(7), null);
				    moveFish(57);
				}
				if(frameCounter >= 2011) {
				    g.drawImage(fish[fishAnimationNum], getX(58), getY(8), null);
				    moveFish(58);
				}
				if(frameCounter >= 2043) {
				    g.drawImage(fish[fishAnimationNum], getX(59), getY(9), null);
				    moveFish(59);
				}
				if(frameCounter >= 2076) {
				    g.drawImage(fish[fishAnimationNum], getX(60), getY(10), null);
				    moveFish(60);
				}
				if(frameCounter >= 2108) {
				    g.drawImage(fish[fishAnimationNum], getX(61), getY(11), null);
				    moveFish(61);
				}
				if(frameCounter >= 2140) {
				    g.drawImage(fish[fishAnimationNum], getX(62), getY(12), null);
				    moveFish(62);
				}
				if(frameCounter >= 2172) {
				    g.drawImage(fish[fishAnimationNum], getX(63), getY(13), null);
				    moveFish(63);
				}
				if(frameCounter >= 2203) {
				    g.drawImage(fish[fishAnimationNum], getX(64), getY(14), null);
				    moveFish(64);
				}
				if(frameCounter >= 2299) {
				    g.drawImage(fish[fishAnimationNum], getX(65), getY(15), null);
				    moveFish(65);
				}
				if(frameCounter >= 2309) {
				    g.drawImage(fish[fishAnimationNum], getX(66), getY(16), null);
				    moveFish(66);
				}
				if(frameCounter >= 2317) {
				    g.drawImage(fish[fishAnimationNum], getX(67), getY(17), null);
				    moveFish(67);
				}
				if(frameCounter >= 2326) {
				    g.drawImage(fish[fishAnimationNum], getX(68), getY(18), null);
				    moveFish(68);
				}
				if(frameCounter >= 2345) {
				    g.drawImage(fish[fishAnimationNum], getX(69), getY(19), null);
				    moveFish(69);
				}
				if(frameCounter >= 2366) {
				    g.drawImage(fish[fishAnimationNum], getX(70), getY(20), null);
				    moveFish(70);
				}
				if(frameCounter >= 2375) {
				    g.drawImage(fish[fishAnimationNum], getX(71), getY(21), null);
				    moveFish(71);
				}
				if(frameCounter >= 2383) {
				    g.drawImage(fish[fishAnimationNum], getX(72), getY(22), null);
				    moveFish(72);
				}
				if(frameCounter >= 2391) {
				    g.drawImage(fish[fishAnimationNum], getX(73), getY(23), null);
				    moveFish(73);
				}
				if(frameCounter >= 2407) {
				    g.drawImage(fish[fishAnimationNum], getX(74), getY(24), null);
				    moveFish(74);
				}
				if(frameCounter >= 2429) {
				    g.drawImage(fish[fishAnimationNum], getX(75), getY(25), null);
				    moveFish(75);
				}
				if(frameCounter >= 2444) {
				    g.drawImage(fish[fishAnimationNum], getX(76), getY(26), null);
				    moveFish(76);
				}
				if(frameCounter >= 2455) {
				    g.drawImage(fish[fishAnimationNum], getX(77), getY(27), null);
				    moveFish(77);
				}
				if(frameCounter >= 2464) {
				    g.drawImage(fish[fishAnimationNum], getX(78), getY(28), null);
				    moveFish(78);
				}
				if(frameCounter >= 2475) {
				    g.drawImage(fish[fishAnimationNum], getX(79), getY(29), null);
				    moveFish(79);
				}
				if(frameCounter >= 2485) {
				    g.drawImage(fish[fishAnimationNum], getX(80), getY(30), null);
				    moveFish(80);
				}
				if(frameCounter >= 2494) {
				    g.drawImage(fish[fishAnimationNum], getX(81), getY(31), null);
				    moveFish(81);
				}
				if(frameCounter >= 2509) {
				    g.drawImage(fish[fishAnimationNum], getX(82), getY(32), null);
				    moveFish(82);
				}
				if(frameCounter >= 2524) {
				    g.drawImage(fish[fishAnimationNum], getX(83), getY(33), null);
				    moveFish(83);
				}
				if(frameCounter >= 2538) {
				    g.drawImage(fish[fishAnimationNum], getX(84), getY(34), null);
				    moveFish(84);
				}
				if(frameCounter >= 2559) {
				    g.drawImage(fish[fishAnimationNum], getX(85), getY(35), null);
				    moveFish(85);
				}
				if(frameCounter >= 2573) {
				    g.drawImage(fish[fishAnimationNum], getX(86), getY(36), null);
				    moveFish(86);
				}
				if(frameCounter >= 2585) {
				    g.drawImage(fish[fishAnimationNum], getX(87), getY(37), null);
				    moveFish(87);
				}
				if(frameCounter >= 2593) {
				    g.drawImage(fish[fishAnimationNum], getX(88), getY(38), null);
				    moveFish(88);
				}
				if(frameCounter >= 2600) {
				    g.drawImage(fish[fishAnimationNum], getX(89), getY(39), null);
				    moveFish(89);
				}
				if(frameCounter >= 2623) {
				    g.drawImage(fish[fishAnimationNum], getX(90), getY(40), null);
				    moveFish(90);
				}
				if(frameCounter >= 2639) {
				    g.drawImage(fish[fishAnimationNum], getX(91), getY(41), null);
				    moveFish(91);
				}
				if(frameCounter >= 2648) {
				    g.drawImage(fish[fishAnimationNum], getX(92), getY(42), null);
				    moveFish(92);
				}
				if(frameCounter >= 2685) {
				    g.drawImage(fish[fishAnimationNum], getX(93), getY(43), null);
				    moveFish(93);
				}
				if(frameCounter >= 2701) {
				    g.drawImage(fish[fishAnimationNum], getX(94), getY(44), null);
				    moveFish(94);
				}
				if(frameCounter >= 2713) {
				    g.drawImage(fish[fishAnimationNum], getX(95), getY(45), null);
				    moveFish(95);
				}
				if(frameCounter >= 2750) {
				    g.drawImage(fish[fishAnimationNum], getX(96), getY(46), null);
				    moveFish(96);
				}
				if(frameCounter >= 2766) {
				    g.drawImage(fish[fishAnimationNum], getX(97), getY(47), null);
				    moveFish(97);
				}
				if(frameCounter >= 2783) {
				    g.drawImage(fish[fishAnimationNum], getX(98), getY(48), null);
				    moveFish(98);
				}
				if(frameCounter >= 2799) {
				    g.drawImage(fish[fishAnimationNum], getX(99), getY(49), null);
				    moveFish(99);
				}
				if(frameCounter >= 2815) {
				    g.drawImage(fish[fishAnimationNum], getX(100), getY(50), null);
				    moveFish(100);
				}
				if(frameCounter >= 2831) {
				    g.drawImage(fish[fishAnimationNum], getX(101), getY(51), null);
				    moveFish(101);
				}
				if(frameCounter >= 2847) {
				    g.drawImage(fish[fishAnimationNum], getX(102), getY(52), null);
				    moveFish(102);
				}
				if(frameCounter >= 2862) {
				    g.drawImage(fish[fishAnimationNum], getX(103), getY(53), null);
				    moveFish(103);
				}
				if(frameCounter >= 2879) {
				    g.drawImage(fish[fishAnimationNum], getX(104), getY(54), null);
				    moveFish(104);
				}
				if(frameCounter >= 2895) {
				    g.drawImage(fish[fishAnimationNum], getX(105), getY(55), null);
				    moveFish(105);
				}
				if(frameCounter >= 2911) {
				    g.drawImage(fish[fishAnimationNum], getX(106), getY(56), null);
				    moveFish(106);
				}
				if(frameCounter >= 2928) {
				    g.drawImage(fish[fishAnimationNum], getX(107), getY(57), null);
				    moveFish(107);
				}
				if(frameCounter >= 2943) {
				    g.drawImage(fish[fishAnimationNum], getX(108), getY(58), null);
				    moveFish(108);
				}
				if(frameCounter >= 2958) {
				    g.drawImage(fish[fishAnimationNum], getX(109), getY(59), null);
				    moveFish(109);
				}
				if(frameCounter >= 2975) {
				    g.drawImage(fish[fishAnimationNum], getX(110), getY(60), null);
				    moveFish(110);
				}
				if(frameCounter >= 2991) {
				    g.drawImage(fish[fishAnimationNum], getX(111), getY(61), null);
				    moveFish(111);
				}
				if(frameCounter >= 3006) {
				    g.drawImage(fish[fishAnimationNum], getX(112), getY(62), null);
				    moveFish(112);
				}
				if(frameCounter >= 3022) {
				    g.drawImage(fish[fishAnimationNum], getX(113), getY(63), null);
				    moveFish(113);
				}
				if(frameCounter >= 3038) {
				    g.drawImage(fish[fishAnimationNum], getX(114), getY(64), null);
				    moveFish(114);
				}
				if(frameCounter >= 3054) {
				    g.drawImage(fish[fishAnimationNum], getX(115), getY(65), null);
				    moveFish(115);
				}
				if(frameCounter >= 3070) {
				    g.drawImage(fish[fishAnimationNum], getX(116), getY(66), null);
				    moveFish(116);
				}
				if(frameCounter >= 3086) {
				    g.drawImage(fish[fishAnimationNum], getX(117), getY(67), null);
				    moveFish(117);
				}
				if(frameCounter >= 3102) {
				    g.drawImage(fish[fishAnimationNum], getX(118), getY(68), null);
				    moveFish(118);
				}
				if(frameCounter >= 3118) {
				    g.drawImage(fish[fishAnimationNum], getX(119), getY(69), null);
				    moveFish(119);
				}
				if(frameCounter >= 3134) {
				    g.drawImage(fish[fishAnimationNum], getX(120), getY(70), null);
				    moveFish(120);
				}
				if(frameCounter >= 3151) {
				    g.drawImage(fish[fishAnimationNum], getX(121), getY(71), null);
				    moveFish(121);
				}
				if(frameCounter >= 3166) {
				    g.drawImage(fish[fishAnimationNum], getX(122), getY(72), null);
				    moveFish(122);
				}
				if(frameCounter >= 3182) {
				    g.drawImage(fish[fishAnimationNum], getX(123), getY(73), null);
				    moveFish(123);
				}
				if(frameCounter >= 3199) {
				    g.drawImage(fish[fishAnimationNum], getX(124), getY(74), null);
				    moveFish(124);
				}
				if(frameCounter >= 3215) {
				    g.drawImage(fish[fishAnimationNum], getX(125), getY(75), null);
				    moveFish(125);
				}
				if(frameCounter >= 3231) {
				    g.drawImage(fish[fishAnimationNum], getX(126), getY(76), null);
				    moveFish(126);
				}
				if(frameCounter >= 3247) {
				    g.drawImage(fish[fishAnimationNum], getX(127), getY(77), null);
				    moveFish(127);
				}
				if(frameCounter >= 3263) {
				    g.drawImage(fish[fishAnimationNum], getX(128), getY(78), null);
				    moveFish(128);
				}
				if(frameCounter >= 3278) {
				    g.drawImage(fish[fishAnimationNum], getX(129), getY(79), null);
				    moveFish(129);
				}
				if(frameCounter >= 3296) {
				    g.drawImage(fish[fishAnimationNum], getX(130), getY(80), null);
				    moveFish(130);
				}
				if(frameCounter >= 3309) {
				    g.drawImage(fish[fishAnimationNum], getX(131), getY(81), null);
				    moveFish(131);
				}
				if(frameCounter >= 3328) {
				    g.drawImage(fish[fishAnimationNum], getX(132), getY(82), null);
				    moveFish(132);
				}
				if(frameCounter >= 3344) {
				    g.drawImage(fish[fishAnimationNum], getX(133), getY(83), null);
				    moveFish(133);
				}
				if(frameCounter >= 3360) {
				    g.drawImage(fish[fishAnimationNum], getX(134), getY(84), null);
				    moveFish(134);
				}
				if(frameCounter >= 3377) {
				    g.drawImage(fish[fishAnimationNum], getX(135), getY(85), null);
				    moveFish(135);
				}
				if(frameCounter >= 3392) {
				    g.drawImage(fish[fishAnimationNum], getX(136), getY(86), null);
				    moveFish(136);
				}
				if(frameCounter >= 3407) {
				    g.drawImage(fish[fishAnimationNum], getX(137), getY(87), null);
				    moveFish(137);
				}
				if(frameCounter >= 3423) {
				    g.drawImage(fish[fishAnimationNum], getX(138), getY(88), null);
				    moveFish(138);
				}
				if(frameCounter >= 3440) {
				    g.drawImage(fish[fishAnimationNum], getX(139), getY(89), null);
				    moveFish(139);
				}
				if(frameCounter >= 3456) {
				    g.drawImage(fish[fishAnimationNum], getX(140), getY(90), null);
				    moveFish(140);
				}
				if(frameCounter >= 3472) {
				    g.drawImage(fish[fishAnimationNum], getX(141), getY(91), null);
				    moveFish(141);
				}
				if(frameCounter >= 3488) {
				    g.drawImage(fish[fishAnimationNum], getX(142), getY(92), null);
				    moveFish(142);
				}
				if(frameCounter >= 3504) {
				    g.drawImage(fish[fishAnimationNum], getX(143), getY(93), null);
				    moveFish(143);
				}
				if(frameCounter >= 3520) {
				    g.drawImage(fish[fishAnimationNum], getX(144), getY(94), null);
				    moveFish(144);
				}
				if(frameCounter >= 3536) {
				    g.drawImage(fish[fishAnimationNum], getX(145), getY(95), null);
				    moveFish(145);
				}
				if(frameCounter >= 3552) {
				    g.drawImage(fish[fishAnimationNum], getX(146), getY(96), null);
				    moveFish(146);
				}
				if(frameCounter >= 3570) {
				    g.drawImage(fish[fishAnimationNum], getX(147), getY(97), null);
				    moveFish(147);
				}
				if(frameCounter >= 3586) {
				    g.drawImage(fish[fishAnimationNum], getX(148), getY(98), null);
				    moveFish(148);
				}
				if(frameCounter >= 3601) {
				    g.drawImage(fish[fishAnimationNum], getX(149), getY(99), null);
				    moveFish(149);
				}
				if(frameCounter >= 3616) {
				    g.drawImage(fish[fishAnimationNum], getX(150), getY(100), null);
				    moveFish(150);
				}
				if(frameCounter >= 3632) {
				    g.drawImage(fish[fishAnimationNum], getX(151), getY(101), null);
				    moveFish(151);
				}
				if(frameCounter >= 3649) {
				    g.drawImage(fish[fishAnimationNum], getX(152), getY(102), null);
				    moveFish(152);
				}
				if(frameCounter >= 3665) {
				    g.drawImage(fish[fishAnimationNum], getX(153), getY(103), null);
				    moveFish(153);
				}
				if(frameCounter >= 3680) {
				    g.drawImage(fish[fishAnimationNum], getX(154), getY(104), null);
				    moveFish(154);
				}
				if(frameCounter >= 3698) {
				    g.drawImage(fish[fishAnimationNum], getX(155), getY(105), null);
				    moveFish(155);
				}
				if(frameCounter >= 3713) {
				    g.drawImage(fish[fishAnimationNum], getX(156), getY(106), null);
				    moveFish(156);
				}
				if(frameCounter >= 3730) {
				    g.drawImage(fish[fishAnimationNum], getX(157), getY(107), null);
				    moveFish(157);
				}
				if(frameCounter >= 3746) {
				    g.drawImage(fish[fishAnimationNum], getX(158), getY(108), null);
				    moveFish(158);
				}
				if(frameCounter >= 3762) {
				    g.drawImage(fish[fishAnimationNum], getX(159), getY(109), null);
				    moveFish(159);
				}
				if(frameCounter >= 3777) {
				    g.drawImage(fish[fishAnimationNum], getX(160), getY(110), null);
				    moveFish(160);
				}
				if(frameCounter >= 3793) {
				    g.drawImage(fish[fishAnimationNum], getX(161), getY(111), null);
				    moveFish(161);
				}
				if(frameCounter >= 3808) {
				    g.drawImage(fish[fishAnimationNum], getX(162), getY(112), null);
				    moveFish(162);
				}
				if(frameCounter >= 3824) {
				    g.drawImage(fish[fishAnimationNum], getX(163), getY(113), null);
				    moveFish(163);
				}
				if(frameCounter >= 3841) {
				    g.drawImage(fish[fishAnimationNum], getX(164), getY(114), null);
				    moveFish(164);
				}
				if(frameCounter >= 3859) {
				    g.drawImage(fish[fishAnimationNum], getX(165), getY(115), null);
				    moveFish(165);
				}
				if(frameCounter >= 3874) {
				    g.drawImage(fish[fishAnimationNum], getX(166), getY(116), null);
				    moveFish(166);
				}
				if(frameCounter >= 3890) {
				    g.drawImage(fish[fishAnimationNum], getX(167), getY(117), null);
				    moveFish(167);
				}
				if(frameCounter >= 3904) {
				    g.drawImage(fish[fishAnimationNum], getX(168), getY(118), null);
				    moveFish(168);
				}
				if(frameCounter >= 3920) {
				    g.drawImage(fish[fishAnimationNum], getX(169), getY(119), null);
				    moveFish(169);
				}
				if(frameCounter >= 3937) {
				    g.drawImage(fish[fishAnimationNum], getX(170), getY(120), null);
				    moveFish(170);
				}
				if(frameCounter >= 3953) {
				    g.drawImage(fish[fishAnimationNum], getX(171), getY(121), null);
				    moveFish(171);
				}
				if(frameCounter >= 3968) {
				    g.drawImage(fish[fishAnimationNum], getX(172), getY(122), null);
				    moveFish(172);
				}
				if(frameCounter >= 3985) {
				    g.drawImage(fish[fishAnimationNum], getX(173), getY(123), null);
				    moveFish(173);
				}
				if(frameCounter >= 4001) {
				    g.drawImage(fish[fishAnimationNum], getX(174), getY(124), null);
				    moveFish(174);
				}
				if(frameCounter >= 4017) {
				    g.drawImage(fish[fishAnimationNum], getX(175), getY(125), null);
				    moveFish(175);
				}
				if(frameCounter >= 4032) {
				    g.drawImage(fish[fishAnimationNum], getX(176), getY(126), null);
				    moveFish(176);
				}
				if(frameCounter >= 4048) {
				    g.drawImage(fish[fishAnimationNum], getX(177), getY(127), null);
				    moveFish(177);
				}
				if(frameCounter >= 4063) {
				    g.drawImage(fish[fishAnimationNum], getX(178), getY(128), null);
				    moveFish(178);
				}
				if(frameCounter >= 4081) {
				    g.drawImage(fish[fishAnimationNum], getX(179), getY(129), null);
				    moveFish(179);
				}
				if(frameCounter >= 4097) {
				    g.drawImage(fish[fishAnimationNum], getX(180), getY(130), null);
				    moveFish(180);
				}
				if(frameCounter >= 4112) {
				    g.drawImage(fish[fishAnimationNum], getX(181), getY(131), null);
				    moveFish(181);
				}
				if(frameCounter >= 4128) {
				    g.drawImage(fish[fishAnimationNum], getX(182), getY(132), null);
				    moveFish(182);
				}
				if(frameCounter >= 4143) {
				    g.drawImage(fish[fishAnimationNum], getX(183), getY(133), null);
				    moveFish(183);
				}
				if(frameCounter >= 4159) {
				    g.drawImage(fish[fishAnimationNum], getX(184), getY(134), null);
				    moveFish(184);
				}
				if(frameCounter >= 4176) {
				    g.drawImage(fish[fishAnimationNum], getX(185), getY(135), null);
				    moveFish(185);
				}
				if(frameCounter >= 4193) {
				    g.drawImage(fish[fishAnimationNum], getX(186), getY(136), null);
				    moveFish(186);
				}
				if(frameCounter >= 4209) {
				    g.drawImage(fish[fishAnimationNum], getX(187), getY(137), null);
				    moveFish(187);
				}
				if(frameCounter >= 4225) {
				    g.drawImage(fish[fishAnimationNum], getX(188), getY(138), null);
				    moveFish(188);
				}
				if(frameCounter >= 4242) {
				    g.drawImage(fish[fishAnimationNum], getX(189), getY(139), null);
				    moveFish(189);
				}
				if(frameCounter >= 4258) {
				    g.drawImage(fish[fishAnimationNum], getX(190), getY(140), null);
				    moveFish(190);
				}
				if(frameCounter >= 4273) {
				    g.drawImage(fish[fishAnimationNum], getX(191), getY(141), null);
				    moveFish(191);
				}
				if(frameCounter >= 4290) {
				    g.drawImage(fish[fishAnimationNum], getX(192), getY(142), null);
				    moveFish(192);
				}
				if(frameCounter >= 4297) {
				    g.drawImage(fish[fishAnimationNum], getX(193), getY(143), null);
				    moveFish(193);
				}
				
				try {
					updateHighScores();
				} catch(Exception e) {
					
				}
			}
			else if(trackNo == 2) {
				// Draw the high score
				g.drawString(String.valueOf(highscore2), 190, 40);
				
				if(frameCounter == 48) {
					playMusic();
				}
			}
			else if(trackNo == 3) {
				// Draw the high score
				g.drawString(String.valueOf(highscore3), 190, 40);
				
				if(frameCounter == 48) {
					playMusic();
				}
			}
		}
		else if(gameState == 2) {
			g.drawImage(about, 0, 0, null);
		}
		else if(gameState == 3) {
			g.drawImage(credits, 0, 0, null);
		}
		else if(gameState == 4) {
			g.drawImage(track1, 0, 0, null);
		}
		else if(gameState == 5) {
			g.drawImage(track2, 0, 0, null);
		} 
		else if(gameState == 6) {
			g.drawImage(track3, 0, 0, null);
		}
	}
	
	// The method playMusic() plays the music
	public static void playMusic() {
		new Thread(new Runnable() {
			public void run() {
				try {
					if(trackNo == 1) {
						ais = AudioSystem.getAudioInputStream(new File("Blue World.wav"));
					}
					else if(trackNo == 2) {
						ais = AudioSystem.getAudioInputStream(new File("Shining Sea.wav"));
					}
					else if(trackNo == 3) {
						ais = AudioSystem.getAudioInputStream(new File("Deep Sea.wav"));
					}
					clip = AudioSystem.getClip();
					clip.open(ais);
					clip.setFramePosition(0);
					clip.start();
				} catch(Exception e) {
				}	
			} 
		}).start(); 
	}
	
	// The method getX returns x-coordinate of the image to be drawn
	public static int getX(int i) {
		return fishCoordinates[i][0];
	}
	
	// The method getY returns y-coordinate of the image to be drawn
	public static int getY(int i) {
		if(i % 2 == 0) {
			return 125;
		}
		return 425;
	}
	
	// The method resetGame resets the game
	public static void resetGame() {
		frameCounter = 0;
		trackNo = 0;
		noteJudgmentText = " ";
		score = 0;
		for(int i = 0; i < fishCoordinates.length; i++) {
			fishCoordinates[i][0] = 0;
		}
		// Reset the music
	    if(clip != null && clip.isRunning()) {
	    	clip.stop();
	    	clip.close();
	    	clip = null;
	    }
	}
	
	// The method removeFish removes the fish from the screen
	public static void removeFish(int i) {
		fishCoordinates[i][0] = 800;
	}
	
	// The method moveFish moves the fish
	public static void moveFish(int i) {
		if(fishCoordinates[i][0] < 800) {
			fishCoordinates[i][0] += 10;
		} 
	}
	
	// The method updateAnimals handles the actions of the animals (the fish and the sharks) including their animation
	public static void updateAnimation() {
		animationFrameCounter++;
		
		if(animationFrameCounter == 4) {
			fishAnimationNum = (fishAnimationNum + 1) % 8;
			animationFrameCounter = 0;
		}	
	}

	// The method judge processes the fish note judgments
	public static boolean judge(int fishX, int i) {
		
		// Early
		if(fishX >= 630 && fishX < 650) {
			score += 5;
			noteJudgmentText = "Early";
			missed = false;
		} 
		
		// Perfect
		else if(fishX >= 670 && fishX <= 680) {
			score += 20;
			noteJudgmentText = "Perfect";
			missed = false;
		}
		
		// Great
		else if(fishX >= 650 && fishX <= 700) {
			score += 10;
			noteJudgmentText = "Great";
			missed = false;
		}
		
		// Late
		else if(fishX > 700 && fishX <= 720) {
			score += 5;
			noteJudgmentText = "Late";
			missed = false;
		}
		
		// Miss
		else {
			noteJudgmentText = "Miss";
		}
		
		return missed;
	}
	
	// The method updateHIghScores updates the high score
	public static void updateHighScores() throws IOException {
		PrintWriter outputFile = new PrintWriter(new FileWriter("highscores.txt"));

		if(trackNo == 1 && score > highscore1) {
			highscore1 = score;
		}
		if(trackNo == 2 && score > highscore2) {
			highscore2 = score;
		}
		if(trackNo == 3 && score > highscore3) {
			highscore3 = score;
		}
		
		outputFile.println(highscore1);
		outputFile.println(highscore2);
		outputFile.println(highscore3);
		outputFile.close();
	}

	
	public static void main(String[] args) {
		try {
			
			// High Score Importation
			Scanner inputFile = new Scanner(new File("highscores.txt"));

			highscore1 = inputFile.nextInt();
			highscore2 = inputFile.nextInt();
			highscore3 = inputFile.nextInt();
			
			// Image Importation
			
			// Track selection screens
			track1 = ImageIO.read(new File("track1.png"));
			track2 = ImageIO.read(new File("track2.png"));
			track3 = ImageIO.read(new File("track3.png"));
			
			// Menu screen
			menu = ImageIO.read(new File("menu.png"));
			
			// Instructions screen
			about = ImageIO.read(new File("about.png"));
			
			// Credits screen
			credits = ImageIO.read(new File("credits.png"));
			
			// In-game screen background
			bg = ImageIO.read(new File("bg.png"));
			
			// Cold Shark
			cShark = ImageIO.read(new File("cShark.png"));
			
			// Dark Shark
			dShark = ImageIO.read(new File("dShark.png"));
			
			// The fish
			for(int i = 0; i < 9; i++) {
				fish[i] = ImageIO.read(new File("fish" + (i + 1) + ".png"));
			}
			
		} catch(Exception e) {		
		}
		JFrame frame = new JFrame("Fish & Beats");
		Fish_and_Beats panel = new Fish_and_Beats();
		frame.add(panel);
		// Reset the music and the game if the window is closed
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	resetGame();
		    }
		});
		frame.setVisible(true);
		frame.pack();
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
				repaint();
			}
			catch(Exception e) {
				
			}
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		
		// The variable lastPressProcessed stores the last time a key press was processed
		double lastPressProcessed = 0;

		if(gameState == 1) {
			// The if statement prevents the user from simply holding down the keys to get points
			if(System.currentTimeMillis() - lastPressProcessed > 100) {
				/* if(e.getKeyChar() == ' ') {
					System.out.println(frameCounter);
				} */
    			if(e.getKeyChar() == ' ') {
    				// Judge the fish notes
    				for(int i = 0; i < fishCoordinates.length; i++) {
    					if(fishCoordinates[i][0] != 800) {
    						int tempFishX = fishCoordinates[i][0];
        					if(!(judge(tempFishX, i))) {
        						removeFish(i);
        						missed = true;
        						break;
        					}
    					}
    				}
    			}
			}
            lastPressProcessed = System.currentTimeMillis();
		} 
		if(gameState == 4) {
			if(e.getKeyCode() == 37) {
				gameState = 6;
			}
			if(e.getKeyCode() == 39) {
				gameState = 5;
			}
		}
		else if(gameState == 5) {
			if(e.getKeyCode() == 37) {
				gameState = 4;
			}
			if(e.getKeyCode() == 39) {
				gameState = 6;
			}
		}
		else if(gameState == 6) {
			if(e.getKeyCode() == 37) {
				gameState = 5;
			}
			if(e.getKeyCode() == 39) {
				gameState = 4;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) { 
		posX = e.getX();
		posY = e.getY();
		
		// The boolean menu is true if the mouse press is on the menu button, and false otherwise
		boolean menu = posX >= 11 && posX <= 132 && posY >= 11 && posY <= 54;
		// The boolean play is true if the mouse press is on the play button in the track selection screens, and false otherwise
		boolean play = posX >= 360 && posX <= 430 && posY >= 460 && posY <= 540;
		// The boolean left is true if the mouse press is on the left arrow button in the track selection screens, and false otherwise
		boolean left = posX >= 35 && posX <= 85 && posY >= 275 && posY <= 325;
		// The boolean right is true if the mouse press is on the play button in the track selection screens, and false otherwise
		boolean right = posX >= 715 && posX <= 765 && posY >= 275 && posY <= 325;
		
		if(gameState == 0) {
			// Detect mouse press on the start button
			if(posX >= 462 && posX <= 622 && posY >= 285 && posY <= 315) {
				gameState = 4;
			}
			
			// Detect mouse press on the about button
			if(posX >= 377 && posX <= 637 && posY >= 337 && posY <= 367) {
				gameState = 2;
			}
			
			// Detect mouse press on the credits button
			if(posX >= 546 && posX <= 706 && posY >= 334 && posY <= 364) {
				gameState = 3;
			}
		}
		if(gameState != 0) {
			if(menu) {
				gameState = 0;
			}
		}
		if(gameState == 4) {
			if(play) {
				trackNo = 1;
				gameState = 1;
			}
			if(left) {
				gameState = 6;
			}
			if(right) {
				gameState = 5;
			}
		}
		else if(gameState == 5) {
			if(play) {
				trackNo = 2;
				gameState = 1;
			}
			if(left) {
				gameState = 4;
			}
			if(right) {
				gameState = 6;
			}
		}
		else if(gameState == 6) {
			if(play) {
				trackNo = 3;
				gameState = 1;
			}
			if(left) {
				gameState = 5;
			}
			if(right) {
				gameState = 4;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	// Useless Methods

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
}
