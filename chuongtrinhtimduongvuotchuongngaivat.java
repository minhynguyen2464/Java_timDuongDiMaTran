package timduongvuotchuongngaivat;

public class chuongtrinhtimduongvuotchuongngaivat {
	static int[][] matran = new int[8][8];
	static int[][] matranOdadi= new int[8][8];
	
	static int X1=0;
	static int Y1=0;
	static int X2=7;
	static int Y2=7;
	static O root=new O(X1,Y1);
	//Xuat ma tran 
	public static void xuatmatran(int[][] matran) {
		for (int i=0;i<8;i++)
		{
			for (int j=0;j<8;j++)
			{
				if ((matran[i][j])==0) System.out.print(" 0 ");
				if ((matran[i][j])==1) System.out.print(" 1 ");
				if ((matran[i][j])==2) System.out.print(" S ");
				if ((matran[i][j])==3) System.out.print(" F ");
				if ((matran[i][j])==4) System.out.print(" X ");
			}
			System.out.println();
		}
	}
	//Tao ma tran dau
	public static int[][] khoitaomatran(){
		int[][] matran = new int[8][8];
		matran[0][0]=2;
		matran[7][7]=3;
		for (int i=2;i<5;i++) matran[i][3]=1;
		for (int j=2;j<5;j++) matran[5][j]=1;
		matran[7][6]=1;
		return matran;
	}
	//tinh khoang cach
	public static double khoangcach(int i1,int j1,int i2,int j2) {
		return Math.sqrt((double)(Math.pow(i2-i1, 2)+Math.pow(j2-j1, 2)));
	}
	//Tim duong den dich su dung khoang cach
	public static void timduongdendich(O root) {
		matranOdadi[root.i][root.j]=1;
		int vitri=0;
		double OF=khoangcach(root.i,root.j,X2,Y2);
		if (OF==0) 
			return;
		else {
			if (root.i-1>=0) //Trai
				if ((matranOdadi[root.i-1][root.j]==0)&&(matran[root.i-1][root.j]==0))
				if (khoangcach(root.i-1,root.j,X2,Y2)<OF) {
					vitri=1;
					OF=khoangcach(root.i-1,root.j,X2,Y2);
				}
			if (root.i+1<=7) //Phai
				if ((matranOdadi[root.i+1][root.j]==0)&&(matran[root.i+1][root.j]==0))
				if (khoangcach(root.i+1,root.j,X2,Y2)<OF) {
					vitri=3;
					OF=khoangcach(root.i+1,root.j,X2,Y2);
				}		
			if (root.j-1>=0) //Tren
				if ((matranOdadi[root.i][root.j-1]==0)&&(matran[root.i][root.j-1]==0))
				if (khoangcach(root.i,root.j-1,X2,Y2)<OF) {
					vitri=2;
					OF=khoangcach(root.i,root.j-1,X2,Y2);
				}	
			if (root.j+1<=7) //Duoi
				if ((matranOdadi[root.i][root.j+1]==0)&&(matran[root.i][root.j+1]==0))
				if (khoangcach(root.i,root.j+1,X2,Y2)<OF) {
					vitri=4;
					OF=khoangcach(root.i,root.j+1,X2,Y2);
				}	
			if ((vitri==0)&&(root.i!=X2)&&(root.j!=Y2)) {
				System.out.println("Di vao ngo cut");
				//Demo
				if ((matranOdadi[root.i-1][root.j]==0)&&(matran[root.i-1][root.j]==0)) //Trai
					{
						vitri=1;
						OF=khoangcach(root.i-1,root.j,X2,Y2);
					}
				if (root.i+1<=7) //Phai
					if ((matranOdadi[root.i+1][root.j]==0)&&(matran[root.i+1][root.j]==0))
					{
						vitri=3;
						OF=khoangcach(root.i+1,root.j,X2,Y2);
					}		
				if (root.j-1>=0) //Tren
					if ((matranOdadi[root.i][root.j-1]==0)&&(matran[root.i][root.j-1]==0))
					{
						vitri=2;
						OF=khoangcach(root.i,root.j-1,X2,Y2);
					}	
				if (root.j+1<=7) //Duoi
					if ((matranOdadi[root.i][root.j+1]==0)&&(matran[root.i][root.j+1]==0))
					 {
						vitri=4;
						OF=khoangcach(root.i,root.j+1,X2,Y2);
					}	
				//EOL
			}
				
			if (vitri==1) {O newO = new O(root.i-1,root.j); root.trai=newO;timduongdendich(root.trai);}
			if (vitri==2) {O newO = new O(root.i,root.j-1); root.tren=newO;timduongdendich(root.tren);}
			if (vitri==3) {O newO = new O(root.i+1,root.j); root.phai=newO;timduongdendich(root.phai);}
			if (vitri==4) {O newO = new O(root.i,root.j+1); root.duoi=newO;timduongdendich(root.duoi);}
		}
	}
	public static void docduongdi(O root) {
		matran[root.i][root.j]=4;
		if (root.tren!=null) docduongdi(root.tren);
		if (root.duoi!=null) docduongdi(root.duoi);
		if (root.trai!=null) docduongdi(root.trai);
		if (root.phai!=null) docduongdi(root.phai);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		matran=khoitaomatran();
		xuatmatran(matran);
		timduongdendich(root);
		docduongdi(root);
		matran[0][0]=2;
		System.out.println("Xuat ket qua tim duong di den dich: "); 
		xuatmatran(matran);
	}

}
