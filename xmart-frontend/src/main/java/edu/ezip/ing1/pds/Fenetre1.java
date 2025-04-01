package edu.ezip.ing1.pds;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import edu.ezip.ing1.pds.business.dto.Article;
import edu.ezip.ing1.pds.business.dto.Articles;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.services.ArticleService;
import edu.ezip.ing1.pds.services.InsertArticle;


/**
 * Fenetre1 : Menu principal, un panneau pour acheter (liste d'articles) 
 * et un panneau pour vendre (publier un nouvel article).
 * 
 * NOTE: Cette version ne contient plus aucune méthode liée à la base de données.
 */
public class Fenetre1 extends JFrame {

    private JPanel contentPanel;

    private JPanel panneauPrincipal;
    private JPanel panneauTransactions;
    private JPanel panneauAcheter;
    private JPanel panneauVendre;

    // Liste pour afficher les articles (ici, seulement local)
    private DefaultListModel<String> articlesListModel;
    private JList<String> articlesList;
  
    private List<Article> articleList = new ArrayList<>();

    // Champs pour le formulaire “Vendre”
    private JTextField txtTitre;
    private JTextArea txtDescription;
    private JTextField txtPrix;
    private JTextField txtTypeTransaction;
    private JTextField txtVille;
    private JTextField txtNomEtudiant;  // Le nom de l'étudiant (à la place d'id_etudiant)
    private Article article;

    private int Index;


    private final static String networkConfigFile = "network.yaml";


    public Fenetre1() throws InterruptedException, IOException {

        super("Fenêtre1");
        
        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        final ArticleService articleService = new ArticleService(networkConfig);

        Articles articles = articleService.selectArticles();
        articleList = new ArrayList<>(articles.getArticles());


        
        setSize(700, 672);
        setLocationRelativeTo(null);

        // Modèle + liste pour afficher les articles
        articlesListModel = new DefaultListModel<>();
        // (Ici, tu peux ajouter quelques exemples si tu veux, en dur)

        for (Index = 0; Index < articleList.size() ; Index++) {

        article = articleList.get(Index);
        articlesListModel.addElement("Nom :" + article.getNom());
        articlesListModel.addElement("Description :" + article.getDescription());
        articlesListModel.addElement("Titre :" +article.getTitre());
        articlesListModel.addElement("Type de Transaction :" + article.getTypeTransaction());
        articlesListModel.addElement("Ville :" + article.getVille());
        articlesListModel.addElement("Prix (€) :" +String.valueOf(article.getPrix()));

        }



        articlesList = new JList<>(articlesListModel);

        // Création des panneaux
        panneauPrincipal = creerPanneauPrincipal();
        panneauTransactions = creerPanneauTransactions();
        panneauAcheter = creerPanneauAcheter();
        panneauVendre = creerPanneauVendre();

        // Panel de contenu (layout principal)
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel);

        // On démarre sur le panneau principal
        setContentPanel(panneauPrincipal);
    }

    /**
     * Remplace le contenu actuel par un autre panneau.
     */
    private void setContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // -------------------------------------------------------------------------
    //  Panneaux
    // -------------------------------------------------------------------------
    /**
     *  Panneau principal
     */
    private JPanel creerPanneauPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Dimension btnDim = new Dimension(600, 120);

        panel.add(Box.createVerticalStrut(50));

        JButton boutonTransactions = new JButton("Acheter / Vendre");
        boutonTransactions.setPreferredSize(btnDim);
        boutonTransactions.addActionListener(e -> setContentPanel(panneauTransactions));
        panel.add(centrer(boutonTransactions));

        panel.add(Box.createVerticalStrut(50));

        JButton boutonQuitter = new JButton("Quitter");
        boutonQuitter.setPreferredSize(btnDim);
        // Ferme toute l'application
        boutonQuitter.addActionListener(e -> System.exit(0));
        panel.add(centrer(boutonQuitter));

        return panel;
    }

    /** Petit utilitaire pour centrer un composant dans un FlowLayout. */
    private JPanel centrer(JComponent comp) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(comp);
        return p;
    }

    /**
     * Panneau “Transactions” : choix entre “Acheter” et “Vendre”
     */
    private JPanel creerPanneauTransactions() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Dimension btnDim = new Dimension(600, 80);

        panel.add(Box.createVerticalStrut(50));

        JButton boutonAcheter = new JButton("Acheter");
        boutonAcheter.setPreferredSize(btnDim);
        boutonAcheter.addActionListener(e -> {
            // Affiche simplement la liste d'articles
            setContentPanel(panneauAcheter);
        });
        panel.add(centrer(boutonAcheter));

        panel.add(Box.createVerticalStrut(50));

        JButton boutonVendre = new JButton("Vendre");
        boutonVendre.setPreferredSize(btnDim);
        boutonVendre.addActionListener(e -> setContentPanel(panneauVendre));
        panel.add(centrer(boutonVendre));

        panel.add(Box.createVerticalStrut(50));

        JButton boutonRetour = new JButton("Retour");
        boutonRetour.setPreferredSize(new Dimension(600, 50));
        boutonRetour.addActionListener(e -> setContentPanel(panneauPrincipal));
        panel.add(centrer(boutonRetour));

        return panel;
    }

    /**
     * Panneau “Acheter” : affiche la liste de tous les articles (ici, stockés localement)
     */
    private JPanel creerPanneauAcheter() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titre = new JLabel("Liste des articles :", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titre, BorderLayout.NORTH);

        JScrollPane sp = new JScrollPane(articlesList);
        panel.add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(e -> setContentPanel(panneauTransactions));
        bottom.add(boutonRetour);

        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    /**
     *  Panneau “Vendre” : formulaire pour insérer un nouvel article (hors DB).
     *  On affiche juste un message pour la démonstration.
     */
    private JPanel creerPanneauVendre() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Nom de l'étudiant
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Nom de l'étudiant : "));
            txtNomEtudiant = new JTextField(20);
            p.add(txtNomEtudiant);
            formPanel.add(p);
        }

        // Titre
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Titre de l'article : "));
            txtTitre = new JTextField(20);
            p.add(txtTitre);
            formPanel.add(p);
        }

        // Description
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Description : "));
            txtDescription = new JTextArea(4, 20);
            p.add(new JScrollPane(txtDescription));
            formPanel.add(p);
        }

        // Prix
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Prix : "));
            txtPrix = new JTextField(10);
            p.add(txtPrix);
            formPanel.add(p);
        }

        // Type transaction
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Type transaction : "));
            txtTypeTransaction = new JTextField(15);
            p.add(txtTypeTransaction);
            formPanel.add(p);
        }

        // Ville
        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Ville : "));
            txtVille = new JTextField(15);
            p.add(txtVille);
            formPanel.add(p);
        }

        // Bouton Publier
        {
    JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btnPublier = new JButton("Publier");
    // Pour la démo, on affiche juste un message
    btnPublier.addActionListener(e -> {
        try {
            String tit = txtTitre.getText();
            String tit1 = txtDescription.getText();
            String tit3 = txtNomEtudiant.getText();
            int tit4 = Integer.parseInt(txtPrix.getText());
            String tit5 = txtVille.getText();
            String tit6 = txtTypeTransaction.getText();
            Article article = new Article(tit3,tit,tit1,2,tit4,tit6,tit5);
            InsertArticle.sendValue("INSERT_ARTICLE", article);

            JOptionPane.showMessageDialog(null, 
            "Article publié avec succès!", 
            "Validé", 
            JOptionPane.INFORMATION_MESSAGE);

            System.out.println(tit);
            System.out.println(tit1);
            System.out.println(tit5);
            System.out.println(tit6);

        } catch (Exception ex) {
            ex.printStackTrace(); // Affiche la trace de l'erreur dans la console
            JOptionPane.showMessageDialog(null, 
                "Une erreur est survenue lors de la publication de l'article : " + ex.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    });
    p.add(btnPublier);
    formPanel.add(p);
}

panel.add(formPanel, BorderLayout.CENTER);


        // Bouton Annuler
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonRetour = new JButton("Annuler");
        boutonRetour.addActionListener(e -> setContentPanel(panneauTransactions));
        bottom.add(boutonRetour);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    // -------------------------------------------------------------------------
    //  Main : Lance simplement l'interface
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Fenetre1 fen;
            try {
                fen = new Fenetre1();
                fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fen.setVisible(true);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        });
    }
}
