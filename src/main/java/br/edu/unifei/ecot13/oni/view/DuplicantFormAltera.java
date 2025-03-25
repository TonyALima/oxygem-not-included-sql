package br.edu.unifei.ecot13.oni.view;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.unifei.ecot13.oni.Atribute;
import br.edu.unifei.ecot13.oni.AtributeEnum;
import br.edu.unifei.ecot13.oni.Duplicant;
import br.edu.unifei.ecot13.oni.DuplicantCondition;
import br.edu.unifei.ecot13.oni.DuplicantConditionEnum;
import br.edu.unifei.ecot13.oni.Humor;
import br.edu.unifei.ecot13.oni.Interest;
import br.edu.unifei.ecot13.oni.PositiveTrait;
import br.edu.unifei.ecot13.oni.Trait;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DuplicantFormAltera extends JFrame {

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
	private JTextPane txtpnNegativeTraits;
	private JTextField txtCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DuplicantFormAltera frame = new DuplicantFormAltera();
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
	public DuplicantFormAltera() {
		setTitle("Alter Duplicant");
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
		txtName.setEnabled(false);
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
		
		JButton btnAlterar = new JButton("Alter");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dup.setName(txtName.getText());
				dup.setAbout(txtarAbout.getText());
				em.getTransaction().begin();
				
				for (AtributeEnum a: AtributeEnum.values()) {
					em.merge(dup.getAtributes().get(a));
				}
				for (DuplicantConditionEnum a: DuplicantConditionEnum.values()) {
					em.merge(dup.getConditions().get(a));
				}
				for (Trait t: dup.getTraits()) {
					em.merge(t);
				}
				for (Interest i: dup.getInterests()) {
					em.merge(i);
				}
				em.merge(dup.getHumor());
				em.merge(dup);
				
				em.getTransaction().commit();
				em.close();
				dispose();
			}
		});
		btnAlterar.setBounds(203, 433, 117, 25);
		contentPane.add(btnAlterar);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dup = (Duplicant) em.createQuery(
						"from Duplicant d " +
						"where d.codigo = " + 
						txtCode.getText()
						).getSingleResult();
					
					txtName.setText(dup.getName());
					txtarAbout.setText(dup.getAbout());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Não encontrado");
					return;
				}
				try {
					Humor h = (Humor) em.createQuery(
							"select d.humor " +
							"from Duplicant d " +
							"where d.codigo = " + 
							txtCode.getText()
							).getSingleResult();
					dup.setHumor(h);
					
					List<Trait> lt = em.createQuery(
						"select d.traits " +
						"from Duplicant d " +
						"where d.codigo = " + 
						txtCode.getText()
						).getResultList();
				
				
					dup.setTraits(lt);
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
					
					List<Interest> li = em.createQuery(
							"select d.interests " +
							"from Duplicant d " +
							"where d.codigo = " + 
							txtCode.getText()
							).getResultList();
					
					dup.setInterests(li);
					String s = "";
					for (Interest i: dup.getInterests()) {
						s += i.getType() + " +" + i.getBonus() + "\n"; 
					}
					txtpnInterests.setText(s);
					
					s = dup.getOverjoyedResponse() + "\n" + dup.getStressReaction();
					txtpnReactions.setText(s);
					
					List<Atribute> la = em.createQuery(
							"select d.atributes " +
							"from Duplicant d " +
							"where d.codigo = " + 
							txtCode.getText()
							).getResultList();
					for (Atribute a : la) {
						dup.getAtributes().put(a.getType(), a);
					}
					
					List<DuplicantCondition> ldc = em.createQuery(
							"select d.conditions " +
							"from Duplicant d " +
							"where d.codigo = " + 
							txtCode.getText()
							).getResultList();
					for (DuplicantCondition dc : ldc) {
						dup.getConditions().put((DuplicantConditionEnum) dc.getType(), dc);
					}
					
					btnAlterar.setEnabled(true);
					txtName.setEnabled(true);
					txtarAbout.setEnabled(true);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Problema com dados do Dup");
					return;
				}
				
			}
		});
		btnFind.setBounds(380, 43, 117, 25);
		contentPane.add(btnFind);
		
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
		
		txtpnNegativeTraits = new JTextPane();
		txtpnNegativeTraits.setEditable(false);
		txtpnNegativeTraits.setForeground(new Color(165, 29, 45));
		txtpnNegativeTraits.setText("NegativeTraits");
		txtpnNegativeTraits.setBounds(137, 280, 219, 60);
		contentPane.add(txtpnNegativeTraits);
		
		txtarAbout = new JTextArea();
		txtarAbout.setEnabled(false);
		txtarAbout.setText("About");
		contentPane.add(txtarAbout);
		
		JScrollPane scrollPane = new JScrollPane(txtarAbout);
		scrollPane.setBounds(137, 45, 219, 55);
		contentPane.add(scrollPane);
		
		txtCode = new JTextField();
		txtCode.setText("Code");
		txtCode.setBounds(383, 12, 114, 19);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
	}
}
