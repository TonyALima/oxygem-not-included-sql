package br.edu.unifei.ecot13.oni.view;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.unifei.ecot13.oni.AtributeEnum;
import br.edu.unifei.ecot13.oni.CookerDuplicant;
import br.edu.unifei.ecot13.oni.Duplicant;
import br.edu.unifei.ecot13.oni.DuplicantConditionEnum;
import br.edu.unifei.ecot13.oni.Interest;
import br.edu.unifei.ecot13.oni.PositiveTrait;
import br.edu.unifei.ecot13.oni.PrintingPod;
import br.edu.unifei.ecot13.oni.ResearcherDuplicant;
import br.edu.unifei.ecot13.oni.Trait;
import br.edu.unifei.ecot13.oni.RandomDuplicant;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DuplicantFormInsere extends JFrame {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("oniPU");
	private EntityManager em = emf.createEntityManager();
	private Duplicant dup;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JLabel lblAbout;
	private JLabel lblInterests;
	private JLabel lblTraits;
	private JLabel lblReactions;
	private JTextArea txtarAbout;
	private JTextPane txtpnPositiveTraits;
	private JTextPane txtpnReactions;
	private JTextPane txtpnInterests;
	private JComboBox<String> comboBoxBuilder;
	private JTextPane txtpnNegativeTraits;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DuplicantFormInsere frame = new DuplicantFormInsere();
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
	public DuplicantFormInsere() {
		setTitle("Insert Duplicant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 12, 126, 24);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(137, 15, 219, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		lblAbout = new JLabel("About: ");
		lblAbout.setBounds(12, 48, 70, 15);
		contentPane.add(lblAbout);
		
		lblInterests = new JLabel("Interests:");
		lblInterests.setBounds(12, 112, 70, 15);
		contentPane.add(lblInterests);
		
		lblTraits = new JLabel("Traits:");
		lblTraits.setBounds(12, 215, 70, 15);
		contentPane.add(lblTraits);
		
		lblReactions = new JLabel("Reactions:");
		lblReactions.setBounds(12, 352, 93, 15);
		contentPane.add(lblReactions);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setEnabled(false);
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dup.setName(txtName.getText());
				dup.setAbout(txtarAbout.getText());
				em.getTransaction().begin();
				em.persist(dup.getHumor());
				for (AtributeEnum a: AtributeEnum.values()) {
					em.persist(dup.getAtributes().get(a));
				}
				for (DuplicantConditionEnum a: DuplicantConditionEnum.values()) {
					em.persist(dup.getConditions().get(a));
				}
				for (Trait t: dup.getTraits()) {
					em.persist(t);
				}
				for (Interest i: dup.getInterests()) {
					em.persist(i);
				}
				em.persist(dup);
				em.getTransaction().commit();
				em.close();
				dispose();
			}
		});
		btnInserir.setBounds(203, 433, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintingPod print = PrintingPod.getInstace();
				
				if (comboBoxBuilder.getSelectedItem().equals("Random"))
					print.setDuplicantBuilder(new RandomDuplicant());
				
				if (comboBoxBuilder.getSelectedItem().equals("Researcher"))
					print.setDuplicantBuilder(new ResearcherDuplicant());
				
				if (comboBoxBuilder.getSelectedItem().equals("Cooker"))
					print.setDuplicantBuilder(new CookerDuplicant());
				
				dup = print.printDuplicant();
				
				txtName.setText(dup.getName());
				txtarAbout.setText(dup.getAbout());
				
				String sp = "";
				String sn = "";
				for (Trait t: dup.getTraits()) {
					if (t instanceof PositiveTrait) {
						sp += t.getType() + "\n";
					}
					else {
						sn += t.getType() + "\n";
					}
				}
				txtpnPositiveTraits.setText(sp);
				txtpnNegativeTraits.setText(sn);
				
				String s = "";
				for (Interest i: dup.getInterests()) {
					s += i.getType() + " +" + i.getBonus() + "\n"; 
				}
				txtpnInterests.setText(s);
				
				s = dup.getOverjoyedResponse() + "\n" + dup.getStressReaction();
				txtpnReactions.setText(s);
				
				txtName.setEnabled(true);
				txtarAbout.setEnabled(true);
				btnInserir.setEnabled(true);
			}
		});
		btnGenerate.setBounds(380, 43, 117, 25);
		contentPane.add(btnGenerate);
		
		txtpnInterests = new JTextPane();
		txtpnInterests.setEditable(false);
		txtpnInterests.setText("Interests");
		txtpnInterests.setBounds(137, 112, 219, 74);
		contentPane.add(txtpnInterests);
		
		txtpnPositiveTraits = new JTextPane();
		txtpnPositiveTraits.setEditable(false);
		txtpnPositiveTraits.setForeground(new Color(38, 162, 105));
		txtpnPositiveTraits.setText("PositiveTraits");
		txtpnPositiveTraits.setBounds(137, 215, 219, 53);
		contentPane.add(txtpnPositiveTraits);
		
		txtpnReactions = new JTextPane();
		txtpnReactions.setEditable(false);
		txtpnReactions.setText("Reactions");
		txtpnReactions.setBounds(137, 352, 219, 54);
		contentPane.add(txtpnReactions);
		
		comboBoxBuilder = new JComboBox<String>();
		comboBoxBuilder.setModel(new DefaultComboBoxModel<String>(new String[] {"Random", "Researcher", "Cooker"}));
		comboBoxBuilder.setBounds(377, 7, 120, 29);
		contentPane.add(comboBoxBuilder);
		
		txtpnNegativeTraits = new JTextPane();
		txtpnNegativeTraits.setEditable(false);
		txtpnNegativeTraits.setForeground(new Color(165, 29, 45));
		txtpnNegativeTraits.setText("NegativeTraits");
		txtpnNegativeTraits.setBounds(137, 280, 219, 60);
		contentPane.add(txtpnNegativeTraits);
		
		txtarAbout = new JTextArea();
		txtarAbout.setText("About");
		contentPane.add(txtarAbout);
		
		JScrollPane scrollPane = new JScrollPane(txtarAbout);
		scrollPane.setBounds(137, 45, 219, 55);
		contentPane.add(scrollPane);
	}
}
