import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	GrilleDeJeu repr�sente une grille de tic tac toe sur laquelle
	on peut cliquer pour choisir la position du prochain X.

	Les positions sur la grille sont index�es de la fa�on suivante :

	0	1	2
	3	4	5
	6	7	8

	La classe GrilleDeJeu ne g�re que l'affichage et le choix du joueur X.
	L'interface TicTacToe d�clare les m�thodes qui seront utilis�es par GrilleDeJeu
	pour communiquer avec la classe qui va g�rer le jeu.
 */

public class GrilleDeJeu extends JFrame implements ActionListener{

    private TicTacToe unJeu;		//R�f�rence sur le jeu
    private JButton[] tab;	//Tableau pour conserver les r�f�rences sur
    						//les boutons
    private int[] tabGagne; //Un tableau qui contiendra les indices de
    						//la combinaison gagnante
    private boolean fini;	//Le jeu est fini
    private Color couleur; 	//Pour conserver la couleur par d�faut des boutons
    private JLabel message;	//Afficher le gagnant ou partie nulle

    //Constructeur pour cr�er l'interface graphique
    public GrilleDeJeu() {
    	int i;
    	JButton bouton;
    	JPanel pGrille = new JPanel();
    	JPanel pControle = new JPanel();
    	JButton reStart;

    	message=new JLabel("Tic Tac Toe");
    	tab = new JButton[9];
    	tabGagne = new int[3];
    	setSize(300,200);
    	pGrille.setLayout(new GridLayout(3,3));
    	setTitle("Tic Tac Toe");

    	for(i = 0; i<9; i++){
    		bouton = new JButton("");
    		bouton.setActionCommand(""+i);
    	    bouton.addActionListener(this);

			pGrille.add(bouton);
			tab[i]=bouton;
    	}
    	couleur = tab[0].getBackground();

    	reStart = new JButton("Nouvelle Partie");
    	reStart.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent evt){
    			initialiser();
    		}
    	});

    	pControle.add(reStart);
    	pControle.add(message);
    	add(pGrille, BorderLayout.CENTER);
    	add(pControle, BorderLayout.SOUTH);

    	setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		unJeu = new Jeu();
		initialiser();
    }

    //R�tablir l'aspect initial de la grille
    private void initialiser(){
    	for( int i = 0; i<9; i++){
    		tab[i].setBackground(couleur);
    		tab[i].setText("");
    	}
    		unJeu.initialise();
    	for(int i=0; i<3; i++)
    		tabGagne[i]=-1;
    	fini = false;
    	message.setText("");
    }

    //M�thode de l'interface ActionListener qui va �tre appel�e lors
    //du click sur un bouton.
    public void actionPerformed (ActionEvent evt) {
    	String arg = evt.getActionCommand();
    	JButton bouton = (JButton)evt.getSource();

    	if(!fini && bouton.getText().equals("")){
    		bouton.setText("X");
    		boutonClique(Integer.parseInt(arg));
    	}

    }

	//Le bouton � l'index i a �t� cliqu�
    void boutonClique(int i){
    	if(fini)
    		return;
    	unJeu.setX(i);				//Transmettre l'index du choix X au jeu
    	if(unJeu.gagnant("X",tabGagne)){	//Victoire de X?
    		fini = true;
    		marque(tabGagne);
    		message.setText("X gagne!");
    	}
    	else
    		if(!unJeu.isPartieNulle()){				 // Si pas victoire de X et pas nulle
    			int cellule = unJeu.getO(); // Demande le choix pour O
    			tab[cellule].setText("O");		 // Modifier l'affichage sur la grille pour
    											 // r�fl�ter le choix de O
    			if(unJeu.gagnant("O",tabGagne)){	 // O gagne?
    				fini = true;
    				marque(tabGagne);
    				message.setText("O gagne!");
    			}
    		}
    		else	//Partie nulle
    		{
    			fini = true;
    			message.setText("Partie nulle!");
    		}
    }

    //changer la couleur des boutons de la combinaison gagnante
    public void marque(int[] t){
    	for(int i=0; i<3;i++){
    		tab[t[i]].setBackground(Color.BLUE);
    	}
    }

    public void testDebug(int t[]){
    	for(int i=0; i<t.length; i++){
    		if( i%2 == 0 && t[i] != -1)
    			tab[t[i]].setText("X");
    		else
    			if(i%2 == 1 && t[i] != -1)
    				tab[t[i]].setText("O");
    	}
    	unJeu.testDebug(t);
    }

    public static void main(String args[]){

    	GrilleDeJeu g = new GrilleDeJeu();

    	/*Exemple d'utilisation de testDebug avec jeu dans l'�tat
    	 *-------
    	 *|x| |O|
    	 *| |x|O|
    	 *| | | |
    	 *-------
    	 */
    	//int[] essai = {0,2,4,5};
    	//g.testDebug(essai);


    }

}