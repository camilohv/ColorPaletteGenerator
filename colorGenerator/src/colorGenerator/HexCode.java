//note: move code into colorGenerator class

package colorGenerator;

import java.math.*;

public class HexCode {
	protected String HCC;
	protected int R_val, G_val, B_val;
	protected int H_val, S_val, L_val;
	protected int C_val, M_val, Y_val, K_val;
	protected String message;
	protected int valid = 0;
	
	//constructors
	
	//HEX CODE
	
	public String getHexCode() {
		// returns the Hex code if the input string is a valid hex code
		if (this.valid == 0){
			return this.HCC;
		}
		// returns an error message if the input string is an invalid hex code
		else {
			return this.message;
		}
	}
	
		//method checks if the input string is a valid hex color code
	public void checkHexCode(int i, int j, String HCC) {
		//all valid hexidecimal characters
		char [] charArray = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'};
		
		//checks if the input string is an invalid hex color code length
		if (HCC.length() > 6) {
			this.valid = 1;
		}
		//if valid length, program checks if the characters it is comprised of are valid
		else {
			// program checks if all characters of the input string are valid hexidecimal characters
			if (i < HCC.length()) {
				if (j < charArray.length) {
					if (Character.toUpperCase(HCC.charAt(i)) == charArray[j]) {
						j = 0;
						i++;
					}
					else {
						j++;
					}
					//recursion is used to check each character
					checkHexCode(i,j, HCC);
				}
				//the input hex color code is invalid if not all characters are a valid hexidecimal character 
				else {
					i++;
					j = 0;
					this.valid = 1;
				}	
			}
		}
	}
	
		//sets the input string as a hex color code
	public void setHexCode(String HCC) {
		// first checks if the input string is even valid
		this.checkHexCode(0,0,HCC);
		if (this.valid == 0){
			// adds the missing 0's to the string if too short
			if (HCC.length() < 6) {
				HCC = HCC + "0";
				setHexCode(HCC);
			}
			else {
				//formats hex code to be all uppercase so it looks pretty :)
				this.HCC = HCC.toUpperCase();	
			}
		}
		/*
		else {
			invalidCode();
		}*/
	}
	
	public String checkHexStrLength(String n) {
		if (n.length() < 2) {
			n = "0" + n;
		}
		return n;
	}
	
	public void convertRGBtoHEX(int R, int G, int B) {
		String R_hex, G_hex, B_hex;
		
		R_hex = checkHexStrLength(Integer.toHexString(R));
		G_hex = checkHexStrLength(Integer.toHexString(G));
		B_hex = checkHexStrLength(Integer.toHexString(B));
		
		this.HCC = R_hex.toUpperCase() + G_hex.toUpperCase() + B_hex.toUpperCase();
		
	}
	//RGB
	
		//sets RGB values based on the Hex Color Code
	public void convertHCCtoRGB(String HCC) {
		// checks if it is a valid hex color code
		this.checkHexCode(0,0,HCC);
		
		if (this.valid == 0){
			// adds the missing 0's to the string if too short
			if (HCC.length() < 6) {
				HCC = HCC + "0";
				convertHCCtoRGB(HCC);
			}
			else {
				//formats hex code to be all uppercase so it looks pretty :)
				this.R_val = Integer.parseInt(HCC.substring(0,2),16);
				this.G_val = Integer.parseInt(HCC.substring(2,4),16);
				this.B_val = Integer.parseInt(HCC.substring(4,6),16);	
			}
		}
		/*
		else {
			invalidCode();
		}*/
	}
	
		// methods that return the individual R, G, and B values respectively
	public int getR_val() {
		return this.R_val;
	}
	
	public int getG_val() {
		return this.G_val;
	}
	
	public int getB_val() {
		return this.B_val;
	}
	
	public void setRGB(int R, int G, int B) {
		this.R_val = R;
		this.G_val = G;
		this.G_val = B;
	}
	
	//HSL
	
	public void convertRGBtoHSL(double R, double G, double B) {
		
		double cmin, cmax, delta;
		int H, S, L;
		
		R = R/255;
		G = G/255;
		B = B/255;
		
		if ((G < R) && (B < R)){
			cmax = R;
			if (G < B) {
				cmin = G;
			}
			else {
				cmin = B;
			}
		}
		else if ((R < G) && (B < G)){
			cmax = G;
			if (R < B) {
				cmin = R;
			}
			else {
				cmin = B;
			}
		}
		else{
			cmax = B;
			if (R < G) {
				cmin = R;
			}
			else {
				cmin = G;
			}
		}
		
		delta = cmax - cmin;
				
		//Hue
		if (delta == 0) {
			H = 0;
		}
		else {
			if (cmax == R){
				H = (int)Math.round((60*((G-B)/delta)+360)%360);
			}
			else if (cmax == G){
				H = (int)Math.round((60*((B-R)/delta)+120)%360);
			}
			else{
				H = (int)Math.round((60*((R-G)/delta)+240)%360);
			}
		}
		
		//H = Math.round(H);
		if (H < 0) {
			H += 360;
		}
		
		//Lightness
		L = (int)Math.round(((cmax + cmin)/2)*100);
		//L = Math.round(L);
		
		//Saturation
		if (delta == 0) {
			S = 0;
		}
		else {
			S = (int)Math.round((delta/cmax)*100);
			//S = Math.round(S);
		}
		
		this.H_val = (int)(H);
		this.S_val = (int)(S);
		this.L_val = (int)(L);
		
	}
	
	public double pos_neg_check(double n) {
		if (n > 1) {
			return (n -= 1);
		}
		if (n < 0) {
			return (n += 1);
		}
		else {
			return n;
		}
	}
	
	public double RGBtempCheck(double temp, double var1, double var2) {
		if ( (6 * temp) < 1 ) {
			return (var2 + (var1 - var2) * 6 * temp);
		}
		else {
			if ( (2 * temp) < 1 ) {
				return var1;
			}
			else {
				if ((3 * temp) < 2) {
					return (var2 +(var1 - var2) * (0.666 - temp) * 6);
				}
				else {
					return var2;
				}
			}
		}
	}
	
	
		//Note: RGB is simply more accurate color code than HSL values, so converting from HSL to RGB will be slightly off.
		//      Nothing can be done about this unfortunately. :(
	public void convertHSLtoRGB(double H, double S, double L) {
		
		double C, Hp, X, m;
		double R, G, B;
		
		S /= 100;
		L /= 100;
		
		//System.out.println(H +"	"+S + "	" + L);
		
		//chroma
		C = ( 1- (Math.abs((2*L)-1) )) * S;
		
		//System.out.println("C: "+C);
		
		//Hue Prime
		Hp = H/60;
		
		
		//intermediate value X for second largest component of the color
		X = C *(1-Math.abs((Hp% 2) -1));
		//System.out.println("X: "+ X);
		
		if ((60 > H) && (H >= 0)) {
			R = C;
			G = X;
			B = 0;
		}
		else if ((120 > H) && (H >= 60)) {
			R = X;
			G = C;
			B = 0;
		}
		else if ((180 > H) && (H >= 120)) {
			R = 0;
			G = C;
			B = X;
		}
		else if ((240 > H) && (H >= 180)) {
			R = 0;
			G = X;
			B = C;
		}
		else if ((300 > H) && (H >= 240)) {
			R = X;
			G = 0;
			B = C;
		}
		else if ((360 > H) && (H >= 300)) {
			R = C;
			G = 0;
			B = X;
		}
		else {
			R = 0;
			G = 0;
			B = 0;
		}
		
		m = L - (C/2);
		
		if (m < 0) {
			m = 0;
		}
		
		//System.out.println("m: "+m);
		
		this.R_val = (int)Math.round((R+m)*255);
		this.G_val = (int)Math.round((G+m)*255);
		this.B_val = (int)Math.round((B+m)*255);
	
	}
	
	public int getH_val() {
		return this.H_val;
	}
	
	public int getS_val() {
		return this.S_val;
	}
	
	public int getL_val() {
		return this.L_val;
	}
	
	public void setHSL(int H, int S, int L) {
		this.H_val = H;
		this.S_val = S;
		this.L_val = L;
	}

	//CMYK
	
		//need to round up values
	public void convertRGBtoCMYK(double R, double G, double B) {
		double cmin, cmax, delta;
		int C, M, Y, K;
		double K_double;
		
		R = R/255;
		G = G/255;
		B = B/255;
		
		if ((G < R) && (B < R)){
			cmax = R;
			if (G < B) {
				cmin = G;
			}
			else {
				cmin = B;
			}
		}
		else if ((R < G) && (B < G)){
			cmax = G;
			if (R < B) {
				cmin = R;
			}
			else {
				cmin = B;
			}
		}
		else{
			cmax = B;
			if (R < G) {
				cmin = R;
			}
			else {
				cmin = G;
			}
		}
		
		delta = cmax - cmin;
		
		K_double = (1 - cmax);
		
		K = (int)Math.round(100*K_double);
		
		C = (int)Math.round(((1 - R - K_double) / (1 - K_double))*100);
		
		M = (int)Math.round(((1 - G - K_double) / (1 - K_double))*100);
		
		Y = (int)Math.round(((1 - B - K_double) / ( 1 - K_double))*100);
		
		this.C_val = C;
		this.M_val = M;
		this.Y_val = Y;
		this.K_val = K;
	}
	
	public int getC_val() {
		return this.C_val;
	}
	
	public int getM_val() {
		return this.M_val;
	}
	
	public int getY_val() {
		return this.Y_val;
	}
	
	public int getK_val() {
		return this.K_val;
	}
	
	public void setCMYK(int C, int M, int Y, int K) {
		this.C_val = C;
		this.M_val = M;
		this.Y_val = Y;
		this.K_val = K;
	}
	
	//error code
	private void invalidCode() {
		this.message = "This is not a valid Hex Color Code.";
	}
	
}
