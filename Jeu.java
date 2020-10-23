
public class Jeu implements TicTacToe {
	private String[] grilleText;      
	private int tour =0;		      
	private boolean termine = false;  


    public Jeu() {
    	grilleText = new String[9];
    	initialise();
    }


    public void initialise(){
    	for(int i = 0 ; i<9; i++){
    		grilleText[i]="";
    	}
    	tour = 0;
    	termine = false;
    }

    public void setX( int cellule){
    	grilleText[cellule] = "X";
    	tour ++;
    }


    private int lePoids(int cellule){
    	if(grilleText[cellule].equals("X"))
    		return 1;
    	else
    		if(grilleText[cellule].equals("O"))
    			return -1;
    		else
    			return 0;
    }



    private void lesPoids(int[] solutions){
    	int n;

    	for(int j = 0; j<3; j++)
    	{	n = 0;
    		for(int i=0; i<3; i++)
    			n += lePoids(i+ j*3);
	    	solutions[j] = n;
    	}

    	for(int j = 0; j<3; j++)
    	{	n = 0;
    		for(int i=0; i<3; i++)
    			n += lePoids(i*3+ j);
	    	solutions[j+3] = n;
    	}

    	n=0;
    	for(int i = 0; i <3 ; i++)
    		n+=lePoids(i*4);
    	solutions[6] = n;

    	n = 0;
    	for(int i = 0; i <3 ; i++)
    		n+=lePoids((i+1)*2);
		solutions[7] = n;
    }


    public int getO(){
    	int[]poids = new int[8];
    	int ch = -1;
    	if(tour==1){
    		if(grilleText[4].equals("X"))
    		{
    			grilleText[0]="O";
    			return 0;
    		}
    		else{
    			grilleText[4]="O";
    			return 4;
    		}
    	}
    	if(tour==2){                     
    		lesPoids(poids);
    		for(int i = 0; i<8; i++){
    			if(poids[i] == 2){       
    				int n = lIndice(i);  
    				grilleText[n] = "O"; 
    				return n;            
    			}
    		}

    		if(!grilleText[0].equals("") && !grilleText[4].equals("") && !grilleText[8].equals(""))
    			if(grilleText[4].equals("O"))
    			{
    				grilleText[1]="O";
    				return 1;
    			}
    			else
    			{
    				grilleText[2]="O";
    				return 2;
    			}
    		else
    			if(!grilleText[6].equals("") && !grilleText[4].equals("") && !grilleText[2].equals(""))
    				if(grilleText[4].equals("O"))
    				{
    					grilleText[1]="O";
    					return 1;
    				}
    				else
    				{
    					grilleText[2]="O";
    					return 2;
    				}
    				else
    					if(grilleText[4].equals("O")){
    						if(grilleText[0].equals("X"))
    						{	if( grilleText[7].equals("X")){
    								grilleText[6]="O";
    								return 6;
    							}
    							else
    							if( grilleText[5].equals("X")){
    								grilleText[2]="O";
    								return 2;
    							}
    						}
    						else
    							if(grilleText[8].equals("X"))
    							{	if (grilleText[1].equals("X")){
    									grilleText[2]="O";
    									return 2;
    								}
    								else
    									if (grilleText[3].equals("X")){
    										grilleText[6]="O";
    										return 6;
    									}
    							}
        						else
    								if(grilleText[2].equals("X"))
    								{	if (grilleText[7].equals("X")){
    										grilleText[8]="O";
    										return 8;
    									}
    									else
    										if (grilleText[3].equals("X")){
    											grilleText[0]="O";
    											return 0;
    										}
    								}

        							else
    									if(grilleText[6].equals("X"))
    									{	if (grilleText[1].equals("X")){
    											grilleText[0]="O";
    											return 0;
    										}
    										else
    											if (grilleText[5].equals("X")){
    												grilleText[8]="O";
    												return 8;
    											}
    									}
    									else
    										if(grilleText[1].equals("X"))
    										{
    											if(grilleText[5].equals("X"))
    											{
    												grilleText[2] = "O";
    												return 2;
    											}
    											else
    												if(grilleText[3].equals("X"))
    												{
    													grilleText[0] = "O";
    													return 0;
    												}
    										}
    										else
    											if(grilleText[7].equals("X"))
    											{
    												if(grilleText[5].equals("X"))
    												{
    													grilleText[8] = "O";
    													return 8;
    												}
	    											else
    													if(grilleText[3].equals("X"))
    													{
    														grilleText[6] = "O";
    														return 6;
    													}
    											}

    					}

    		}

    	
    	lesPoids(poids);
    	for(int i = 0; i<8; i++){
    		if(poids[i] == -2){      
    			int n = lIndice(i);  
    			grilleText[n] = "O"; 
    			return n;
    		}
    		if(poids[i]==2) 
    			ch = i;     
    	}                   

    	if(ch >=0){			
    		int n = lIndice(ch);  
    		grilleText[n]="O";
    		return n;
    	}
    	ch = -1;

    	
    	for(int i =0; i<8; i++){
    		if(poids[i]==-1)    
    			if(lIndice(i)>=0) 
    				ch=i;		  
    	}

    	if(ch>=0){   
    		int n = lIndice(ch);  
    		grilleText[n]="O";    
    		return n;             
   		}                         
   		                          

    	for(int i=0; i<8; i++)    
    		if(lIndice(i)>=0){    
    			int n = lIndice(i);
    			grilleText[n]="O";
    			return n;
    		}
    	return -1;
    }
    
    private int lIndice(int pos){
    	if(pos <3)
    	{
    		for(int i = 0; i<3; i++)
    			if(grilleText[pos *3 + i].equals(""))
    				return pos*3 +i;
    	}
    	else
    		if(pos < 6)
    		{	for(int i = 0; i<3; i++)
    				if(grilleText[3 * i+ pos-3].equals(""))
    					return 3*i + pos -3;
    		}
    		else
    			if(pos==6){
    				for(int i = 0; i<3; i++)
    					if(grilleText[i*4].equals(""))
    						return i *4;

    			}
    			else
    				if(pos==7){
    					for(int i = 0; i<3; i++)
    						if(grilleText[(i+1)*2].equals(""))
    							return (i+1)*2;
    				}
    	return -1;

    }
   
    public boolean isPartieNulle(){
    	return tour==5;
    }
	
    public boolean gagnant(String joueur, int[] pos ){
    //	System.out.println("joueur "+ joueur);

    		if(grilleText[0].equals(joueur) && grilleText[1].equals(joueur)&& grilleText[2].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=1;
    			pos[2]=2;
    			return true;
    		}
    		if(grilleText[0].equals(joueur) && grilleText[4].equals(joueur)&& grilleText[8].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=4;
    			pos[2]=8;
    			return true;
    		}
    		if(grilleText[0].equals(joueur) && grilleText[3].equals(joueur)&& grilleText[6].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=3;
    			pos[2]=6;
    			return true;
    		}

    			if(grilleText[6].equals(joueur) && grilleText[7].equals(joueur)&& grilleText[8].equals(joueur))
    			{
    				pos[0]=6;
    				pos[1]=7;
    				pos[2]=8;
    				return true;
    			}
    			if(grilleText[6].equals(joueur) && grilleText[4].equals(joueur)&& grilleText[2].equals(joueur))
    			{
    				pos[0]=6;
    				pos[1]=4;
    				pos[2]=2;
    				return true;
    			}

       			if(grilleText[3].equals(joueur)&& grilleText[4].equals(joueur)&& grilleText[5].equals(joueur))
    			{
    				pos[0]=3;
    				pos[1]=4;
    				pos[2]=5;
    				return true;
    			}
    			if(grilleText[1].equals(joueur)&& grilleText[4].equals(joueur)&& grilleText[7].equals(joueur))
    			{
    				pos[0]=1;
    				pos[1]=4;
    				pos[2]=7;
    				return true;
    			}
    			if(grilleText[2].equals(joueur)&& grilleText[8].equals(joueur)&& grilleText[5].equals(joueur))
    			{
    				pos[0]=2;
    				pos[1]=5;
    				pos[2]=8;
    				return true;
    			}


    	return false;
    }


     public void testDebug(int[] t){
    	for(int i=0; i<t.length; i++){
    		if( i%2 == 0 && t[i] != -1) {
    			grilleText[t[i]] = "X";
    			tour++;
    		}
    		else
    			if(i%2 == 1 && t[i] != -1)
    				grilleText[t[i]] ="O";
    	}

     }

}