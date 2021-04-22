//note: make program generate random Hex color code values
//program tells the user whether an input Hex value is valid
//In order to be valid, the input hex value must be:
//less than or equal to 6 characters long
//contain digits between 0-9
//contain letters between A-F
//
//note: program should eventually generate multiple palettes
//monochromatic, analogus, triadic, complementary, and split complementary palettes

//program asks user to enter hex code and returns RGB HSL and CYMK values
//program checks if entered hex color code is valid
//note to self: errors should probably state why the error occurred
package colorGenerator;

import java.util.*;

public class colorGenerator {

	public static void main(String[] args) {
		//executes the color program
		
		colorGenerator();
	}
	
	public static void colorGenerator() {
		Scanner kb = new Scanner(System.in);
		//sets default hex
		String HCC = "000000";
		//prompts user to enter hex value
		
		
		System.out.println("Enter a Hex Color Code: ");
		HCC = kb.nextLine();
		
		//program creates new hex object
		HexCode X = new HexCode();
		
		X.checkHexCode(0,0,HCC);
		if (X.valid == 0) {
			X.setHexCode(HCC);
			X.convertHCCtoRGB(HCC);
			X.convertRGBtoHSL(X.getR_val(), X.getG_val(), X.getB_val());
			X.convertRGBtoCMYK(X.getR_val(), X.getG_val(), X.getB_val());
			
			System.out.println("BASE COLOR");
			colorInfo(X);
			
			System.out.println("COMPLEMENTARY");
			complementary(X);
			System.out.println("ANALAGOUS");
			analagous(X);
			System.out.println("TRIADIC");
			triadic(X);
			System.out.println("SPLIT COMPLEMENTARY");
			split_complementary(X);
		}
		else {
			System.out.println("This hex code is invalid.");
			System.out.println();
		}
		
		colorGenerator();
	}
	
	public static void colorInfo(HexCode X) {
		System.out.println("	Hex:	#" + X.getHexCode());
		
		System.out.println("	RGB:	" + X.getR_val()+"	"+ X.getG_val()+"	"+ X.getB_val());
		
		System.out.println("	HSL:	" + X.getH_val()+ "	" + X.getS_val()+ "	"+ X.getL_val());
		
		System.out.println("	CMYK:	"+ X.getC_val()+ "	" + X.getM_val()+ "	"+ X.getY_val() + "	"+ X.getK_val());
		
		System.out.println();
	}
	
	public static int check360(int d) {
		if (d > 360) {
			d -= 360;
		}
		else if (d <0) {
			d += 360;
		}
		return d;
	}
	
	public static void newColor_Hue(HexCode B, HexCode N, int H) {
		N.setHSL(H, B.getS_val(), B.getL_val());
		N.convertHSLtoRGB(N.getH_val(), N.getS_val(), N.getL_val());
		N.convertRGBtoCMYK(N.getR_val(), N.getG_val(), N.getB_val());
		N.convertRGBtoHEX(N.getR_val(), N.getG_val(), N.getB_val());
	}
	
	public static void complementary(HexCode X) {
		HexCode C = new HexCode();
		int Comp_H;
		
		Comp_H = 180+X.getH_val();
		
		Comp_H = check360(Comp_H);
		
		newColor_Hue(X, C, Comp_H);
		
		colorInfo(C);
	}
	
	public static void analagous(HexCode X) {
		HexCode A1 = new HexCode();
		HexCode A2 = new HexCode();
		int aH1, aH2;
		
		aH1 = 30+X.getH_val();
		aH2 = -30+X.getH_val();
		
		aH1 = check360(aH1);
		aH2 = check360(aH2);
		
		newColor_Hue(X, A1, aH1);
		newColor_Hue(X, A2, aH2);
		
		colorInfo(A1);
		colorInfo(A2);
	}
	
	public static void triadic(HexCode X) {
		HexCode T1 = new HexCode();
		HexCode T2 = new HexCode();
		int tH1, tH2;
		
		tH1 = 120+X.getH_val();
		tH2 = 240+X.getH_val();
		
		tH1 = check360(tH1);
		tH2 = check360(tH2);
		
		newColor_Hue(X, T1, tH1);
		newColor_Hue(X, T2, tH2);
		
		colorInfo(T1);
		colorInfo(T2);
	}
	
	public static void split_complementary(HexCode X) {
		HexCode C1 = new HexCode();
		HexCode C2 = new HexCode();
		int cH1, cH2;
		
		cH1 = 150+X.getH_val();
		cH2 = 210+X.getH_val();
		
		cH1 = check360(cH1);
		cH2 = check360(cH2);
		
		newColor_Hue(X, C1, cH1);
		newColor_Hue(X, C2, cH2);
		
		colorInfo(C1);
		colorInfo(C2);
	}
	
	
}










	/*
	public static void hexCheck(int i, int j, String HCC) {
		char [] charArray = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'};
		
		if (HCC.length() < 6) {
			HCC = HCC + "0";
			hexCheck(i,j,HCC);
		}
		
		else {
			if (i < HCC.length()) {
				if (j < charArray.length) {
					if (Character.toUpperCase(HCC.charAt(i)) == charArray[j]) {
						j = 0;
						i++;
					}
					else {
						j++;
					}
					hexCheck(i,j,HCC);
				}
				else {
					i++;
					j = 0;
					invalidCode();
				}	
			}
		}
	}
	
	private static void invalidCode() {
		System.out.println("This is not a valid Hex Color Code.");
	}*/
