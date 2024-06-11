package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.BanqueFacade;
import modele.Compte;
import modele.Operation;
import view.DialogNouveauCompte;
import view.DialogNouvelleOperation;

public class BanqueView extends JFrame {

	private JPanel contentPane;
	JComboBox<Compte> comboComptes = null;
	JLabel labelCompte = new JLabel("num\u00E9ro de compte");
	JLabel labelSolde = new JLabel("solde");
	JList<Operation> listeOperations = null;
	DefaultListModel<Operation> listmodelOperations=new DefaultListModel<Operation>();
	// le compte qui est choisi via la combobox
	Compte compteSelectionne=null;

	BanqueFacade facade = new BanqueFacade();
	
	/*
	 * Cr�er ici un lien avec la fa�ade BanqueFacade
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanqueView frame = new BanqueView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BanqueView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel paneauCompte = new JPanel();
		paneauCompte.setBackground(SystemColor.inactiveCaption);
		paneauCompte.setBounds(10, 11, 754, 104);
		contentPane.add(paneauCompte);
		paneauCompte.setLayout(null);
		comboComptes = new JComboBox<Compte>();		

		for (Compte c : facade.getLesComptes())
		{
			comboComptes.addItem(c);
		}

		if(comboComptes.getItemCount() != 0){
			comboComptes.setSelectedIndex(0);
		}

		compteSelectionne = (Compte)comboComptes.getSelectedItem(); 
		comboComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JComboBox<String> comboComptes = (JComboBox)e.getSource();
		        compteSelectionne = (Compte)comboComptes.getSelectedItem(); 
				chargerCompteSelectionne();
			}
		});
		comboComptes.setBounds(107, 27, 438, 22);
		paneauCompte.add(comboComptes);
		
		JLabel lblChoisirUnCompte = new JLabel("Choisir un compte");
		lblChoisirUnCompte.setBounds(10, 31, 93, 14);
		paneauCompte.add(lblChoisirUnCompte);
		
		JButton boutonAjouterUnCompte = new JButton("Ajouter un compte");
		boutonAjouterUnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterCompte();
			}
		});
		boutonAjouterUnCompte.setBounds(555, 27, 189, 23);
		paneauCompte.add(boutonAjouterUnCompte);
		
		JLabel lblNumroDeCompte = new JLabel("Compte num\u00E9ro");
		lblNumroDeCompte.setBounds(10, 60, 93, 14);
		paneauCompte.add(lblNumroDeCompte);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setBounds(10, 79, 48, 14);
		paneauCompte.add(lblSolde);
				
		labelCompte.setBackground(SystemColor.inactiveCaptionBorder);
		labelCompte.setBounds(107, 60, 249, 14);
		paneauCompte.add(labelCompte);
		
		labelSolde.setBackground(SystemColor.inactiveCaptionBorder);
		labelSolde.setBounds(107, 79, 48, 14);
		paneauCompte.add(labelSolde);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 126, 754, 426);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel labelListeDesOprations = new JLabel("Liste des op\u00E9rations");
		labelListeDesOprations.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelListeDesOprations.setBounds(45, 11, 195, 31);
		panel.add(labelListeDesOprations);
		
	    listeOperations = new JList<Operation>(listmodelOperations);
		listeOperations.setBounds(20, 53, 519, 362);
		panel.add(listeOperations);
		
		JButton boutonAjouterOpration = new JButton("Ajouter une op\u00E9ration");
		boutonAjouterOpration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterOperation();				
			}
		});
		boutonAjouterOpration.setBounds(549, 50, 195, 23);
		panel.add(boutonAjouterOpration);
		
		chargerCompteSelectionne();
	}
	private void chargerCompteSelectionne()
	{		
		listmodelOperations.removeAllElements();
		if(compteSelectionne != null) {
			Compte compte = facade.findCompte(compteSelectionne.getCle());
			labelCompte.setText(compte.getNumCompte());
			labelSolde.setText(compte.getSolde().toString());
		}
	}
	private void ajouterCompte()
	{
		Compte compte=null;
		DialogNouveauCompte jd = new DialogNouveauCompte();
		jd.setVisible(true);
		if (jd.isSaisieOK())
		{
			compte = facade.ajouterCompte(jd.getNumeroCompte(),jd.getSolde());
			comboComptes.addItem(compte);
		}
	}
	
	private void ajouterOperation()
	{
		DialogNouvelleOperation jd = new DialogNouvelleOperation();
		jd.setVisible(true);
		if (jd.isSaisieOK())
		{
			/*
			 * Ajouter une nouvelle op�ration
			 */
			//Compte compte = facade.ajouterOperation(jd.getIntitule(), jd.getDate(), jd.getMontant(), compteSelectionne);

		}
	}
}
