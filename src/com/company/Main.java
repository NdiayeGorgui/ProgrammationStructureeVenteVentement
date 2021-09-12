package com.company;

import java.util.Scanner;
import iofichiertab.*;
import java.io.File;

public class Main {

    //////       DECLARATION DES VARIABLES  ////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int ctr = 0;
    public static String desative = "p";
    public static String homme = "Homme";
    public static String femme = "Femme";
    public static String unisex = "Unisexe";
    public static String codeHomme = "h";
    public static String codeFemme = "f";
    public static String codeUnisex = "u";
    public static String actif = "Actif";
    public static String inactif = "Périmé";
    public static String interne = "Local";
    public static String externe = "Externe";
    public static String tiret = "***********";
    public static int ind;
    public static int numero;
    public static boolean trouve;
    public static String choice;
    static final int Dim = 100;
    public static int[] tabnumitem = new int[Dim];
    public static String[] tabdescription = new String[Dim];
    public static String[] tabsexe = new String[Dim];
    public static int[] taboriginevetement = new int[Dim];
    public static String[] taboriginevetement2 = new String[Dim];
    public static double[] tabprixvente = new double[Dim];
    public static double[] tabprixrevient = new double[Dim];
    public static int[] tabquantite = new int[Dim];
    public static int[] tabquantitemin = new int[Dim];
    public static int[] tabnumfournisseur = new int[Dim];
    public static int[] tabnumfournisseur2 = new int[Dim];
    public static String[] tabnomfournisseur = new String[Dim];
    public static String[] tabtelephoneFournisseur = new String[Dim];
    public static String[] tabstatut = new String[Dim];

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String fichier = "dataTableaux.txt";      //nom du fichier de données
        final String SEPARATEUR = ";";      //Separateur des champs de le fichier
        boolean fin = false;
        boolean finmodif;
        int choix;
        int choixmodif;

        // Ouverture du fichier en lecture avec en parametre tous les tableaux
        if (new File(fichier).exists()) {
            ctr = IOFichierTab.lireFichier(fichier, SEPARATEUR, tabnumitem, tabdescription, tabsexe, tabprixrevient, tabprixvente, tabquantite, tabquantitemin, tabnumfournisseur, tabnumfournisseur2, tabnomfournisseur, tabtelephoneFournisseur, tabstatut, taboriginevetement, taboriginevetement2);

        }
        while (fin == false) {

            afficherMenu();
            verifierSaisie();
            choix = Integer.parseInt(choice);

            switch (choix) {
                case 0:
                    fin = true;
                    System.out.println("\n A bientôt !.... \n");
                    break;
                case 1:
                    ajouter();
                    break;
                case 2:
                    lister();
                    break;
                case 3: {
                    finmodif = false;
                    while (finmodif == false) {
                        afficherMenuModifier();
                        verifierSaisie();
                        choixmodif = Integer.parseInt(choice);
                        switch (choixmodif) {
                            case 0:
                                finmodif = true;
                                break;
                            case 1:
                                modifierQuantite(tabquantite);
                                break;
                            case 2:
                                modifierPrix(tabprixrevient);
                                break;
                            case 3:
                                modifierPrix(tabprixvente);
                                break;
                            case 4:
                                modifierOrigine(taboriginevetement);
                                break;
                            case 5:
                                lister();
                                break;
                            default:
                                System.out.println("\nVous avez saisi une option invalide ! ");
                                break;
                        }
                    }
                }
                break;
                case 4:
                    supprimer(tabnumitem);
                    break;
                case 5:
                    afficherInventaire();
                    break;
                case 6:
                    valeurTotaleInventaire();
                    break;
                case 7:
                    gestionFournisseur();
                    break;
                default:
                    System.out.println("\nVous avez saisi une option invalide !");
            }
        }
        // Ouverture du fichier en ecriture
        IOFichierTab.ecrireFichier(fichier, SEPARATEUR, ctr, tabnumitem, tabdescription, tabsexe, tabprixrevient, tabprixvente, tabquantite, tabquantitemin, tabnumfournisseur, tabnumfournisseur2, tabnomfournisseur, tabtelephoneFournisseur, tabstatut, taboriginevetement, taboriginevetement2);

    }
    // TOUTES LES FONCTIONS ET PROCEDURES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*********************************************************************************************************
     cette methode permet d'afficher le titre du menu
     *********************************************************************************************************/
    public  static void afficherTitre(){
        System.out.println("\n=====================================================");
        System.out.println("               Gestion de stocks");
        System.out.println("                 des vêtements");
        System.out.println("             M E R K E Z Y   D E P O T");
        System.out.println("=====================================================");
        System.out.println("                     MENU");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu principal
     *********************************************************************************/
    public static void afficherMenu() {
        afficherTitre();
        System.out.println("\nQue voulez vous faire ?   \n");
        System.out.println("\t\t1 : Ajouter un vêtement");
        System.out.println("\t\t2 : Lister tous les vêtements");
        System.out.println("\t\t3 : Modifier un vêtement");
        System.out.println("\t\t4 : Supprimer ou désactiver un vêtement");
        System.out.println("\t\t5 : Obtenir une liste d’inventaires de vêtements");
        System.out.println("\t\t6 : Obtenir la valeur totale de l’inventaire des vêtements");
        System.out.println("\t\t7 : Gestion des Fournisseurs");
        System.out.println("\t\t0 : Quitter l'application");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu modifier
     *********************************************************************************/
    public static void afficherMenuModifier() {
        afficherTitre();
        System.out.println("\nQue voulez vous modifier ?  : \n");
        System.out.println("\t\t1 : Quantité");
        System.out.println("\t\t2 : Prix de revient");
        System.out.println("\t\t3 : Prix de vente");
        System.out.println("\t\t4 : Origine");
        System.out.println("\t\t5 : Lister");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu Inventaire
     *********************************************************************************/
    public static void affichermenuInventaire() {
        afficherTitre();
        System.out.println("\nQue voulez vous afficher ?   \n");
        System.out.println("\t\t1 : la liste des vêtements  actifs ");
        System.out.println("\t\t2 : la liste des vêtements périmés ");
        System.out.println("\t\t3 : la liste des vêtements par sexe");
        System.out.println("\t\t4 : la liste des vêtements par origine");
        System.out.println("\t\t5 : la liste des items dont la quantité en inventaire est < au seuil minimal ");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu Sexe
     *********************************************************************************/
    public static void affichermenuSexe() {
        afficherTitre();
        System.out.println("\nQue voulez vous afficher ?   \n");
        System.out.println("\t\t1 : la liste des vêtements  homme ");
        System.out.println("\t\t2 : la liste des vêtements femme ");
        System.out.println("\t\t3 : la liste des vêtements unisexe");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu origine du vetement
     *********************************************************************************/
    public static void affichermenuOrigine() {
        afficherTitre();
        System.out.println("\nQue voulez vous afficher ?   \n");
        System.out.println("\t\t1 : la liste des vêtements fabriqués en interne");
        System.out.println("\t\t2 : la liste des vêtements fabriqués par une autre entreprise");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu supprimer
     *********************************************************************************/
    public static void affichermenuSupprimer() {
        afficherTitre();
        System.out.println("\nQue voulez vous faire ?   \n");
        System.out.println("\t\t1 : Retirer ce vêtement  de l'inventaire");
        System.out.println("\t\t2 : Rendre ce vêtement inactif");
        System.out.println("\t\t3 : Lister");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu de la valeur total de l'inventaire
     *********************************************************************************/
    public static void affichermenuValeurTotal() {
        afficherTitre();
        System.out.println("\nQue voulez vous afficher ?   \n");
        System.out.println("\t\t1 : La valeur totale de l’inventaire par rapport au prix de revient");
        System.out.println("\t\t2 : La valeur totale de l’inventaire par rapport au prix de vente");
        System.out.println("\t\t0 : Retour");
    }
    /********************************************************************************
     cette methode permet d'afficher le menu fournisseur
     *********************************************************************************/
    public static void affichermenuFournisseur() {
        afficherTitre();
        System.out.println("\nQue voulez vous faire ? \n");
        System.out.println("\t\t1 : Afficher la liste des fournisseurs ");
        System.out.println("\t\t2 : Afficher la liste des vêtements achetés aupres d'un fournisseur ");
        System.out.println("\t\t3 : Modifier le nom d'un fournisseur");
        System.out.println("\t\t4 : Modifier le numéro de téléphone d'un fournisseur");
        System.out.println("\t\t0 : Retour");
    }
    /*********************************************************************************************************
     cette methode permet de lister tous les vetements actif de l'inventaire en utilisant deux sous fonctions
     pour l'affichage selon le car il y'a un formatage a faire sur les numeros de telephones
     des produits externe ce qui n'est pas le cas avec les produits internes
     *********************************************************************************************************/
    public static void lister() {

        afficherEntete();
        for (int i = 0; i < ctr; i++) {
            if (taboriginevetement[i] != 0) {
                if (actif.equals(tabstatut[i])) {
                    afficherVetementExterne(i);
                }
            } else if (actif.equals(tabstatut[i])) {
                afficherVetementLocal(i);
            }
        }
    }
    /**************************************************************************************************************
     cette methode permet de lister aussi les enregistrements a la difference de l'autre fonction lister(),
     lui on l'utilise pour lister les tableaux avec un certain nombre de criteres qui n'existe pas dans le premier
     **************************************************************************************************************/
    public static void afficherTableau(int i) {
        if (taboriginevetement[i] != 0) {
            afficherVetementExterne(i);
        } else
            afficherVetementLocal(i);
    }
    /**************************************************************************************************************
     cette methode permet d'afficher l'entete de la fonction lister detail fournisseur
     **************************************************************************************************************/
    public static void afficherEnteteDetailFournisseurs() {
        String ch;
        ch = fixeS("", 102, "=");
        System.out.println(ch);
        ch = fixeS("N°Vêtement", 15, null) + "| "
                + fixeS("****  Description ***", 30, null) + "| "
                + fixeS("* Sexe *", 10, null) + "| "
                + fixeS("Prix de revient", 17, null) + "| "
                + fixeS("Quantité", 10, null) + "| "
                + fixeS(" Statut ", 10, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 102, "=");
        System.out.println(ch);
    }
    //***********************************************************************
    //Procédure permettant d'affichier l'entête de la liste des fournisseurs
    //***********************************************************************
    public static void afficherEnteteFournisseurs() {
        String ch;
        ch = fixeS("", 59, "=");
        System.out.println(ch);
        ch = fixeS("N°Fournisseur", 15, null) + "| "
                + fixeS("****  Nom Fournisseur**", 25, null) + "| "
                + fixeS("** Téléphone **", 15, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 59, "=");
        System.out.println(ch);
    }
    /**************************************************************************************************************
     cette methode permet d'afficher tous les fournisseurs, le tableau est d'abord trier puis enlever les doublons
     si un fournisseur apparait plus d'une fois on l'affiche une seule fois
     **************************************************************************************************************/
    public static void afficherFournisseur() {

        for (int i = 0; i < ctr; i++) {

            if ((taboriginevetement[i] > 0)) {
                if (tabnumfournisseur2[i] > 0) {
                    afficherFournisseur(i);
                }
            }
        }
    }
    //**************************************************************
    //Procédure qui permet de formater et d'afficher un fournisseur
    //**************************************************************
    public static void afficherFournisseur(int indice) {
        String ch;
        ch = fixeS(Integer.toString(tabnumfournisseur2[indice]), 15, null) + "| "
                + fixeS(tabnomfournisseur[indice], 25, null) + "| "
                + fixeS("("+ tabtelephoneFournisseur[indice].substring(0, 3) + ") "
                + tabtelephoneFournisseur[indice].substring(3, 6)
                + "-" + tabtelephoneFournisseur[indice].substring(6, 10), 15, null) + "| ";

        System.out.println(ch);
        ch = fixeS("", 59, "-");
        System.out.println(ch);
    }
    /*********************************************************************************************************************
     cette methode permet de rechercher et d'afficher liste des vêtements achetés auprès d’un fournisseur en particulier.
     *********************************************************************************************************************/
    public static void detailFournisseur( int tabNum[]) {
        ind=0;
        String fournisseurs = "Saisir le numéro du fourniseur: ";
        validationNumero(numero, fournisseurs);
         trouve = false;

        while ((trouve == false) && (ind < ctr)) {

            if (numero == (tabNum[ind])) {
                trouve = true;
            } else {
                ind++;
            }
        }
        if (trouve) {
            tabnumfournisseur[ind] = numero;
            System.out.println("\nFournisseur: " + tabnomfournisseur[ind] + " \n");
            System.out.println("\nTéléphone: " + "("+ tabtelephoneFournisseur[ind].substring(0, 3) + ") "
                    + tabtelephoneFournisseur[ind].substring(3, 6) + "-"
                    + tabtelephoneFournisseur[ind].substring(6, 10) + " \n");
            afficherEnteteDetailFournisseurs();
            for (int i = 0; i < ctr; i++) {
                if ((taboriginevetement[i] > 0) && (tabnumfournisseur[ind] == tabnumfournisseur[i]))
                    afficherListeDetailFournisseur(i);
            }
        } else
            System.out.println("\nCe numéro de fournisseur n'existe pas !\n");
    }
    //*****************************************************
    //Fonction qui retourne une chaine de longueur fixe;
    //La chaine initiale est tronquée si trop longue, ou
    //complétée avec le padding si trop courte
    //*****************************************************
    public static String fixeS(String s, int longueur, String pad) {
        String chaineFixe = "";
        String espace = " ";
        boolean fin = false;
        int i = 0;

        if (pad == null) {
            pad = espace;
        }

        while (i < longueur) {
            if (i == s.length()) {
                fin = true;
            }
            if (!fin) {
                chaineFixe = chaineFixe + s.charAt(i);
            } else {
                chaineFixe = chaineFixe + pad;
            }
            i++;
        }
        return chaineFixe;
    }
    //*****************************************************
    //Procédure permettant d'affichier l'entête de la liste
    //*****************************************************
    public static void afficherEntete() {
        String ch;
        ch = fixeS("", 201, "=");
        System.out.println(ch);
        ch = fixeS("N°Vêtement", 10, null) + "| "
                + fixeS("**** Description ***", 30, null) + "| "
                + fixeS("** Sexe ** ", 15, null) + "| "
                + fixeS("Prix de revient", 16, null) + "| "
                + fixeS("Prix de vente", 16, null) + "| "
                + fixeS("Quantité", 9, null) + " | "
                + fixeS("Qté min", 9, null) + "| "
                + fixeS("N° Fournisseur", 15, null) + "| "
                + fixeS("** Nom Fournisseur **", 25, null) + "| "
                + fixeS("** Téléphone **", 15, null) + "| "
                + fixeS("Statut", 9, null) + "| "
                + fixeS("Origine", 9, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 201, "=");
        System.out.println(ch);
    }
    //*****************************************************
    //Procédure qui permet de formater et d'afficher un item
    //*****************************************************
    public static void afficherVetementExterne(int indice) {
        String ch;
        ch = fixeS(Integer.toString(tabnumitem[indice]), 10, null) + "| "
                + fixeS(tabdescription[indice], 30, null) + "| "
                + fixeS(tabsexe[indice], 15, null) + "| "
                + fixeS(Double.toString(tabprixrevient[indice]), 15, null) + "$| "
                + fixeS(Double.toString(tabprixvente[indice]), 15, null) + "$| "
                + fixeS(Integer.toString(tabquantite[indice]), 10, null) + "| "
                + fixeS(Integer.toString(tabquantitemin[indice]), 9, null) + "| "
                + fixeS(Integer.toString(tabnumfournisseur[indice]), 15, null) + "| "
                + fixeS(tabnomfournisseur[indice], 25, null) + "| "
                + fixeS("("+ tabtelephoneFournisseur[indice].substring(0, 3) + ") "
                + tabtelephoneFournisseur[indice].substring(3, 6)
                + "-" + tabtelephoneFournisseur[indice].substring(6, 10), 15, null) + "| "
                + fixeS(tabstatut[indice], 9, null) + "| "
                + fixeS(taboriginevetement2[indice], 9, null) + "| ";

        System.out.println(ch);
        ch = fixeS("", 201, "-");
        System.out.println(ch);
    }
    //*****************************************************
    //Procédure qui permet de formater et d'afficher un item
    //*****************************************************
    public static void afficherVetementLocal(int indice) {
        String ch;

        ch = fixeS(Integer.toString(tabnumitem[indice]), 10, null) + "| "
                + fixeS(tabdescription[indice], 30, null) + "| "
                + fixeS(tabsexe[indice], 15, null) + "| "
                + fixeS(Double.toString(tabprixrevient[indice]), 15, null) + "$| "
                + fixeS(Double.toString(tabprixvente[indice]), 15, null) + "$| "
                + fixeS(Integer.toString(tabquantite[indice]), 10, null) + "| "
                + fixeS(Integer.toString(tabquantitemin[indice]), 9, null) + "| "
                + fixeS(Integer.toString(tabnumfournisseur[indice]), 15, null) + "| "
                + fixeS(tabnomfournisseur[indice], 25, null) + "| "
                + fixeS( tabtelephoneFournisseur[indice], 15, null) + "| "
                + fixeS(tabstatut[indice], 9, null) + "| "
                + fixeS(taboriginevetement2[indice], 9, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 201, "-");
        System.out.println(ch);
    }
    /**************************************************************************************************************
     cette methode permet d'afficher liste details des fournisseurs
     **************************************************************************************************************/
    public static void afficherListeDetailFournisseur(int indice) {
        String ch;

        ch = fixeS(Integer.toString(tabnumitem[indice]), 15, null) + "| "
                + fixeS(tabdescription[indice], 30, null) + "| "
                + fixeS(tabsexe[indice], 10, null) + "| "
                + fixeS(Double.toString(tabprixrevient[indice]), 16, null) + "$| "
                + fixeS(Integer.toString(tabquantite[indice]), 10, null) + "| "
                + fixeS(tabstatut[indice], 10, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 102, "-");
        System.out.println(ch);
    }
    /**************************************************************************************************************
     cette methode permet d'ajouter un vetement avec toutes les validations requises
     **************************************************************************************************************/
    public static void ajouter() {
        String messagePrixRevient = "Saisir le prix de revient: ";
        String messagePrixVente = "Saisir le prix de vente: ";
        String messageQte = "Saisir la quantité: ";
        String messageQteMin = "Saisir la quantité minimum: ";
        String messageOrigine = "Saisir l'origine du vêtement (0 : fabriqué par l’entreprise) et (nombre > 0 : fabriqué par une autre entreprise): ";
        validationItem(ctr);
        validationDescription(ctr);
        validationSexe(ctr);
        validationPrix(tabprixrevient, ctr, messagePrixRevient);
        validationPrix(tabprixvente, ctr, messagePrixVente);
        validationQuantite(tabquantite, ctr, messageQte);
        validationQuantite(tabquantitemin, ctr, messageQteMin);
        validationOrigineVetement(ctr, tabnumfournisseur, messageOrigine);
        System.out.println("\n Enregistrement du vêtement N° " + tabnumitem[ctr] + " effectué avec succés !\n ");
        ctr++;
    }
    /**************************************************************************************************************
     cette methode permet de rechercher l'existance d'un item s'il le trouve elle renvoie true
     **************************************************************************************************************/
    public static void itemexiste() {
         trouve = false;
        while ((trouve == false) && (ind < ctr)) {
            if (numero == (tabnumitem[ind])) {
                trouve = true;
            } else {
                ind++;
            }
        }
    }
    /**************************************************************************************************************
     cette fonction permet de verifier si tous les caracteres saisies sont des chiffres
     **************************************************************************************************************/
    public static boolean tousDesChiffres(String chaine) {
        boolean tousDesChiffres = true;
        int idx = 0;
        while (tousDesChiffres && idx < chaine.length()) {
            if (!Character.isDigit(chaine.charAt(idx))) {
                tousDesChiffres = false;
            } else {
                idx++;
            }
        }
        return tousDesChiffres;
    }
    /********************************************************************************************************
     cette methode fait la validation du prix saisie par l'utilisateur, le prix doit etre compris entre
     1 et $ 10 000 exclu et aussi empecher la saisie vide ou string
     *********************************************************************************************************/
    public static void validationPrix(double tabPrix[], int ctr, String message) {
        boolean fin = false;
        String input = "";

        do {
            do {
                System.out.print(message);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Saisie null !");
                } else if (!estUnDouble(input))
                    System.out.println("Valeur incorrecte !");
                else {
                    tabPrix[ctr] = Double.parseDouble(input);
                    fin = true;
                }
            } while (!fin);
            if (tabPrix[ctr] < 0 || tabPrix[ctr] > 10000)
                System.out.println("le prix  doit être  positif et inférieur a $ 10 000 ! ");

        } while (tabPrix[ctr] < 0 || tabPrix[ctr] > 10000);
    }
    /***************************************************************************************************
     cette methode permet la validation de la quantité saisie par l'utilisateur, elle doit etre positive
     et aussi empecher la saisie vide ou de string
     ***************************************************************************************************/
    public static void validationQuantite(int tabqte[], int ctr, String message) {

        boolean fin = false;
        String input = "";
        do {

            do {
                System.out.print(message);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Saisie null !");

                } else if (!estUnInt(input))
                    System.out.println("Valeur incorrecte !");
                else {
                    tabqte[ctr] = Integer.parseInt(input);
                    fin = true;
                }
            } while (!fin);
            if (tabqte[ctr] < 0)
                System.out.println("La quantité doit être  positive !");
        } while (tabqte[ctr] < 0);
    }
    /********************************************************************************************************************
     cette methode permet de modifier un prix, le produit est d'abord recherché, s'il est trouvé
     on appelle la fonction validationPrix a l'interieur pour controler la saisie
     **********************************************************************************************************************/
    public static void modifierPrix(double tabPrix[]) {
         trouve = false;
        ind = 0;
        String messagePrix = "Saisir le nouveau prix: ";
        String item = "Saisir le numéro de l'item dont vous voulez modifier le prix: ";
        validationNumero(numero, item);
        itemexiste();
        if (trouve) {
            validationPrix(tabPrix, ind, messagePrix);
            System.out.println("\n Le prix du vêtement N°" + numero + " a été modifié avec succés !\n");
        } else
            System.out.println("Ce numéro n'existe pas !");
    }
    /***********************************************************************************************************
     cette methode permet de modifier une quantité, le produit est d'abord recherché, s'il est trouvé,
     on appelle la fonction validationQuantité a l'interieur pour controler la saisie
     ************************************************************************************************************/
    public static void modifierQuantite(int tabQte[]) {
         trouve = false;
        ind = 0;
        String item = "Saisir le numéro de l'item dont vous voulez modifier la quantité: ";
        String messageQte = "Saisir la nouvelle quantité: ";
        validationNumero(numero, item);

        itemexiste();
        if (trouve) {
            validationQuantite(tabQte, ind, messageQte);
            System.out.println("\n La quantité du vêtement N°" + numero + " a été modifié avec succés !\n");
        } else
            System.out.println("\nCe numéro n'existe pas !\n");
    }
    /**************************************************************************************************************************
     cette methode permet de modifier  l'origine du vetement,on le change en externe si c'etait un produit fabriqué en interne
     dans ce cas il faut créer un fournisseur avec ses toutes ses infos
     si c'est un produit fabriqué en externe et qu'on veut changer en local, on supprime le fournisseur et ses infos
     **************************************************************************************************************************/
    public static void modifierOrigine(int tabOrigine[]) {
         trouve = false;
        ind = 0;
        String item = "Saisir le numéro de l'item dont vous voulez modifier l'origine: ";
        validationNumero(numero, item);
        itemexiste();
        if (trouve) {
            if (tabOrigine[ind] > 0) {
                changeOrigineEnLocal(tabOrigine, ind);
            } else if (tabOrigine[ind] == 0) {
                changeOrigineEnExterne(tabOrigine, ind);
            }
        } else
            System.out.println("\nCe numéro n'existe pas !\n");
    }
    /*********************************************************************************************
     cette methode permet de modifier le nom du fournisseur
     si le fournisseur apparait plus d'une fois dans la base elle va remlpacer le nom
     pour tous les items du fournisseur indiqué
     ******************************************************************************************/
    public static void modifierNomFournisseur(String tabnom[], int tabNum[]) {
         trouve = false;
        ind = 0;
        String fournisseur = "Saisir le numéro du fourniseur dont vous voulez modifier le nom: ";
        validationNumero(numero, fournisseur);
        while ((trouve == false) && (ind < ctr)) {

            if (numero == (tabNum[ind])) {
                trouve = true;
            } else {
                ind++;
            }
        }
        if (trouve) {
            if (taboriginevetement[ind] != 0) {

                System.out.println("\nFournisseur: " + tabnomfournisseur[ind]+"\n" );

                changerNomFournisseur(tabnom, ind);
            } else
                System.out.println("\nDésolé ce numéro n'est pas attribué aux fourniseurs !\n");
        } else
            System.out.println("\nCe numéro de fournisseur n'existe pas !\n");
    }
    /***********************************************************************************************
     cette methode permet de modifier le numero de telephone avec les validations requises
     si le fournisseur apparait plus d'une fois dans la base elle va remlpacer le numero de telephone
     pour tous les items du fournisseur indiqué
     ***********************************************************************************************/
    public static void modifierNumTelephoneFournisseur(String tabNumTel[], int tabNum[]) {
         trouve = false;
        ind = 0;
        String fournisseur = "Saisir le numéro du fourniseur dont vous voulez modifier le téléphone: ";
        validationNumero(numero, fournisseur);
        while ((trouve == false) && (ind < ctr)) {

            if (numero == (tabNum[ind])) {
                trouve = true;
            } else {
                ind++;
            }
        }
        if (trouve) {
            if (taboriginevetement[ind] != 0) {
                System.out.println("");
                System.out.println("Fournisseur: " + tabnomfournisseur[ind] + " ");
                System.out.println("\nSon ancien numéro de Téléphone: " + "("+ tabtelephoneFournisseur[ind].substring(0, 3) + ") "
                        + tabtelephoneFournisseur[ind].substring(3, 6) + "-"
                        + tabtelephoneFournisseur[ind].substring(6, 10) + " \n");

                changerNumeroTelephone(tabNumTel, ind);
            } else
                System.out.println("\nDésolé ce numéro n'est pas attribué aux fourniseurs !\n");
        } else
            System.out.println("\nCe numéro de fournisseur n'existe pas !\n");
    }
    /**************************************************************************************************
     cette methode permet desactiver le statut d'un produit, par defaut tout produit créer est activé
     ici nous demandons a l'utilisateur de saisir le caractere p qui permet de désactiver le produit
     et le placé dans le lot des produits périmés
     **************************************************************************************************/
    public static void desactiverStatut(String tabStat[], int ind) {
        if (tabStat[ind].equals(actif)) {
            System.out.print("Saisir le caractere  'P' pour désactiver le statut: ");
            tabStat[ind] = sc.nextLine();

            if (tabStat[ind].equalsIgnoreCase(desative)) {
                tabStat[ind] = inactif;
                System.out.println("\n Le statut du vêtement N°" + numero + " a été désactivé avec succés !\n");
            } else {
                System.out.print("\nLe caratere saisi est incorrect !\n ");
                tabStat[ind] = actif;
            }
        }
    }
    /**************************************************************************************************************
     cette methode permet de changer l'orine d'un produit qui etait externe en interne,
     on supprime le fournisseur et ses infos et on change l'origine pour le mettre en local
     **************************************************************************************************************/
    public static void changeOrigineEnLocal(int tabOrigine[], int ind) {
        boolean fin = false;
        String input = "";
        /////////////////////////////////
        System.out.println("\nCe vêtement est fabriqué en externe\n");
        do {
            System.out.print("\nSaisir 0 (zéro) pour changer l'origine en local: ");
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else if (!estUnInt(input))
                System.out.println("La valeur saisie est incorrecte !");
            else {
                tabnumfournisseur[ind] = Integer.parseInt(input);

                if (tabnumfournisseur[ind] == 0) {
                    tabtelephoneFournisseur[ind] = tiret;
                    tabnomfournisseur[ind] = tiret;
                    taboriginevetement[ind] = 0;
                    taboriginevetement2[ind] = interne;
                    System.out.println("\n L'origine du vêtement N°" + tabnumitem[ind] + " a été modifié avec succés !\n");
                    fin = true;
                } else {
                    System.out.println("\nLa valeur saisie est incorrecte !\n");
                }
            }
        } while (!fin);
    }
    /**************************************************************************************************************
     cette methode permet de changer l'orine d'un produit qui etait interne en externe,
     on crée le fournisseur et ses infos et on change l'origine pour le mettre en externe
     **************************************************************************************************************/
    public static void changeOrigineEnExterne(int tabOrigine[], int ind) {

        String message = "Saisir un Numéro (nombre > 0 ) pour changer l'origine en Externe: ";
        System.out.println("\nCe vêtement est fabriqué en local\n");
        validationOrigine(ind, tabOrigine, message);
        System.out.println("\n L'origine du vêtement N°" + tabnumitem[ind] + " a été modifié avec succés !\n");
    }
    /********************************************************************************************************************
     cette methode permet de changer le nom du fournisseur on l'on utilisera dans modifierFournisseur pour changer le nom
     *********************************************************************************************************************/
    public static void changerNomFournisseur(String tabnom[], int ind) {
        String input="";
        boolean fin=false;
        do {
            System.out.print("Saisir le nouveau nom: ");
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else fin = true;
        }
        while (!fin);

        tabnom[ind] = input;
        for (int i = 0; i < ctr; i++) {
            if (numero == (taboriginevetement[i]))
                tabnom[i] = tabnom[ind].toUpperCase();
        }
        System.out.println("\n Le nom du fournisseur  N°" + numero + " a été modifié avec succés !\n");
    }
    /********************************************************************************************************************
     cette methode permet de modifier le numero de telephone on appele la fonction savenumTel pour les validations
     *********************************************************************************************************************/
    public static void changerNumeroTelephone(String tabNumTel[], int ind) {
        saveNumTelephone(ind);
        for (int i = 0; i < ctr; i++) {
            if (numero == (tabnumfournisseur[i]))
                tabtelephoneFournisseur[i] = tabNumTel[ind];
        }
        System.out.println("\n Le Telephone du fournisseur  N°" + numero + " a été modifié avec succés !\n");
    }
    /********************************************************************************************************************
     cette metode permet de supprimer un enregistrement
     on recherche d'abord l'item s'il est trouvé, on appelle la fonction deleterecord qui se charge de la suppression
     ou de la fonction modier statut si on veux le placé dans le lot des produits périmés
     *********************************************************************************************************************/
    public static void supprimer(int tabNum[]) {
      boolean  finsup = false;
      int choixsup;
         trouve = false;
        ind = 0;
        String item = "Saisir le numéro de l'item que vous voulez supprimer ou désactiver: ";
        validationNumero(numero, item);
        itemexiste();
        if (!trouve) {
            System.out.println("\nCe Numero n'existe pas\n ");
        } else {

            while (finsup == false) {

                affichermenuSupprimer();
                verifierSaisie();
                choixsup = Integer.parseInt(choice);
                switch (choixsup) {
                    case 0:
                        finsup = true;
                        break;
                    case 1:
                        if (trouve) {
                            for (int i = ind; i < ctr - 1; i++) {
                                deleteRecord(tabNum, i);
                            }
                            ctr--;
                            System.out.println("\n Le vêtement N°" + numero + " a été retiré de l'inventaire !\n");
                        } else
                            System.out.println("\n Le vêtement N°" + numero + " n'existe plus dans l'inventaire !\n");
                        break;
                    case 2:
                        desactiverStatut(tabstatut, ind);
                        break;
                    case 3:
                        lister();
                        break;
                    default:
                        System.out.println("\nVous avez saisi une option invalide !");
                        break;
                }
            }
        }
    }
    /*********************************************************************************************
     cette methode permet d'afficher la liste des inventaires
     *********************************************************************************************/
    public static void afficherInventaire() {
       boolean finliste = false;
        boolean finlistorigin;
        boolean finlistsex;
        int choixlist;
        int choixsex;
        int choixorigin;
        while (finliste == false) {

            affichermenuInventaire();
            verifierSaisie();
            choixlist = Integer.parseInt(choice);

            switch (choixlist) {
                case 0:
                    finliste = true;
                    break;
                case 1:
                    System.out.println(" la liste des vêtements  actifs ");
                    lister();
                    break;
                case 2:
                    System.out.println("la liste des vêtements  inactifs ");
                    afficherEntete();
                    for (int i = 0; i < ctr; i++) {
                        if (inactif.equalsIgnoreCase(tabstatut[i])) {
                            afficherTableau(i);
                        }
                    }
                    break;
                case 3:
                    finlistsex = false;
                    while (finlistsex == false) {
                        affichermenuSexe();
                        verifierSaisie();
                        choixsex = Integer.parseInt(choice);
                        switch (choixsex) {
                            case 0:
                                finlistsex = true;
                                break;
                            case 1:
                                System.out.println("la liste des vêtements pour homme: ");
                                afficherEntete();
                                for (int i = 0; i < ctr; i++) {
                                    if (tabsexe[i].equalsIgnoreCase(homme) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                                        afficherTableau(i);
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("la liste des vêtements pour femme: ");
                                afficherEntete();
                                for (int i = 0; i < ctr; i++) {
                                    if (tabsexe[i].equalsIgnoreCase(femme) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                                        afficherTableau(i);
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("la liste des vêtements unisexe: ");
                                afficherEntete();
                                for (int i = 0; i < ctr; i++) {
                                    if (tabsexe[i].equalsIgnoreCase(unisex) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                                        afficherTableau(i);
                                    }
                                }
                                break;
                            default:
                                System.out.println("\nVous avez saisi une option invalide !");
                                break;
                        }
                    }
                    break;
                case 4:
                    finlistorigin = false;
                    while (finlistorigin == false) {
                        affichermenuOrigine();
                        verifierSaisie();
                        choixorigin = Integer.parseInt(choice);
                        switch (choixorigin) {
                            case 0:
                                finlistorigin = true;
                                break;
                            case 1:
                                System.out.println("la liste des vêtements fabriqués par l'entreprise: ");

                                afficherEntete();
                                for (int i = 0; i < ctr; i++) {
                                    if ((taboriginevetement[i] == 0) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                                        afficherTableau(i);
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("la liste des vêtements fabriqués par une autre entreprise: ");
                                afficherEntete();
                                for (int i = 0; i < ctr; i++) {
                                    if ((taboriginevetement[i] > 0) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                                        afficherTableau(i);
                                    }
                                }
                                break;
                            default:
                                System.out.println("\nVous avez saisi une option invalide !");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("la liste des vêtements dont la quantité est inferieur au seuil minimum: ");

                    afficherEntete();
                    for (int i = 0; i < ctr; i++) {
                        if ((tabquantite[i] < tabquantitemin[i]) && (actif.equalsIgnoreCase(tabstatut[i]))) {
                            afficherTableau(i);
                        }
                    }
                    break;
                default:
                    System.out.println("\nVous avez saisi une option invalide !");
                    break;
            }
        }
    }
    /****************************************************************************************************************
     cette metode permet d'afficher la valeur total de l'inventaire suivant le prix de revient et le prix de vente
     ****************************************************************************************************************/
    public static void valeurTotaleInventaire() {
       boolean finlistinventaire = false;
       int choixinventaire;
        while (finlistinventaire == false) {
            affichermenuValeurTotal();
            verifierSaisie();
            choixinventaire = Integer.parseInt(choice);
            switch (choixinventaire) {
                case 0:
                    finlistinventaire = true;
                    break;
                case 1:
                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("La valeur totale de l’inventaire des vêtements par rapport au prix de revient= " + afficherMontantTotal(tabprixrevient) + " $");
                    System.out.println("---------------------------------------------------------------------------------------");
                    break;
                case 2:

                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("La valeur totale de l’inventaire des vêtements par rapport au prix de vente= " + afficherMontantTotal(tabprixvente) + " $");
                    System.out.println("-------------------------------------------------------------------------------------");
                    break;
                default:
                    System.out.println("\nVous avez saisi une option invalide !");
            }
        }
    }
    /*********************************************************************************************************
     cette fonction permet de calculer le montant total de l'inventaire
     *********************************************************************************************************/
    public static double afficherMontantTotal(double tabMontanttot[]) {
        double montantTotalPrix = 0;
        double[] tabmontantPrix = new double[100];
        for (int i = 0; i < ctr; i++) {
            if (actif.equalsIgnoreCase(tabstatut[i])) {
                tabmontantPrix[i] = (tabMontanttot[i] * tabquantite[i]);
                montantTotalPrix = (montantTotalPrix + tabmontantPrix[i]);
            }
        }
        return montantTotalPrix;
    }
    /********************************************************************************************************************
     cette methode permet l'nregistrement d'un numero de telephone avec les validations requises,
     on oblige a l'utilisateur de saisir 10 chiffres
     en effet nous voulons avoir un format unique lors de l'affichage des numero, donc apres avoir saisi le numero,
     le programme pourra se charger de formater l'affichage
     *********************************************************************************************************************/
    public static void saveNumTelephone(int ctr) {
        do {
            System.out.print("Saisir le numéro de téléphone du fournisseur a 10 chiffres (sans separateur) : ");
            tabtelephoneFournisseur[ctr] = sc.nextLine();
            if (!tousDesChiffres(tabtelephoneFournisseur[ctr]))
                System.out.println("Valeur incorrecte !");
            else if (tabtelephoneFournisseur[ctr].length() < 10) {
                System.out.println("Le numero de telephone est incomplet !");
            } else if (tabtelephoneFournisseur[ctr].length() > 10)
                System.out.println("Le numero de telephone depasse 10 chiffres !");
        } while (!tousDesChiffres(tabtelephoneFournisseur[ctr])
                || (tabtelephoneFournisseur[ctr].length() < 10)
                || (tabtelephoneFournisseur[ctr].length() > 10));
    }
    /*********************************************************************************************************
     cette methode permet d'enregistrer un fournisseur en mettant son nom en majuscule
     *********************************************************************************************************/
    public static void saveNomFournisseur(int ctr) {
        System.out.print("Saisir le nom du fournisseur : ");
        tabnomfournisseur[ctr] = sc.nextLine();
        tabnomfournisseur[ctr] = tabnomfournisseur[ctr].toUpperCase();
    }
    /*********************************************************************************************************
     cette methode permet la validation du numéro de l'item avec la verication de la saisie nulle,
     du signe, du type et enfin de la plage
     la premiere boucle renvoie un message d'erreur si l'utilisateur saisi une valeur hors plage c'est q dire
     les 4 chiffres maximum
     la deuxieme boucle verifie la saisie nulle et la valeur non numérique
     *********************************************************************************************************/
    public static void validationItem(int ctr) {
        boolean fin = false;
        String input = "";
        do {
            do {
                System.out.print("Saisir le numero de l'item a 4 chiffres: ");
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Saisie null !");
                } else if (!estUnInt(input))
                    System.out.println("La valeur saisie est incorrecte !");
                else {
                    tabnumitem[ctr] = Integer.parseInt(input);
                    if ((tabnumitem[ctr] >= 1000) && (tabnumitem[ctr] <= 9999)) {
                        for (int i = 0; i < ctr; i++) {
                            do {
                                if (tabnumitem[ctr] == tabnumitem[i]) { //cette condition verifie l'existance du code a 4 chiffre saisie
                                    System.out.println("ce numero existe déja !");
                                    System.out.print("Saisir a nouveau le numero de l'item a 4 chiffres: ");
                                    input = sc.nextLine();
                                    if (!estUnInt(input))
                                        System.out.println("La valeur saisie est incorrecte !");
                                    else {
                                        tabnumitem[ctr] = Integer.parseInt(input);
                                    }
                                }
                            } while (tabnumitem[ctr] == tabnumitem[i]);
                        }
                        fin = true;
                    } else
                        System.out.println("La valeur saisie est incorrecte !");
                }
            } while (!fin);
        } while ((tabnumitem[ctr] < 1000) || (tabnumitem[ctr] > 9999));
    }
    /*********************************************************************************************************
     cette methode permet la validation de la description en obligeant a l'utilisateur
     que le premier caractere soit une lettre
     *********************************************************************************************************/
    public static void validationDescription(int ctr) {
        boolean fin = false;
        String input = "";
        do {
            do {
                System.out.print("Saisir la description : ");
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Saisie null !");
                } else fin = true;
            }
            while (!fin);
            tabdescription[ctr] = input;
            if (!Character.isLetter(tabdescription[ctr].charAt(0)))
                System.out.println("Le premier caractere doit etre une lettre!");
        } while (!Character.isLetter(tabdescription[ctr].charAt(0)));
    }
    /*********************************************************************************************************
     cette methode permet la validation du sexe du produit,on oblige au user de saisir l'une des caracteres énumérés
     faute de quoi il pourra pas continuer la saisie
     *********************************************************************************************************/
    public static void validationSexe(int ctr) {
        boolean fin = false;
        String input = "";
        do {
            System.out.print("Saisir le sexe ('H' pour homme 'F' pour femme et 'U' pour unisexe): ");
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else fin = true;
        }
        while (!fin);
        tabsexe[ctr] = input;
        while (!codeHomme.equalsIgnoreCase(tabsexe[ctr])
                && !codeFemme.equalsIgnoreCase(tabsexe[ctr])
                && !codeUnisex.equalsIgnoreCase(tabsexe[ctr])) {
            System.out.print("\nle caractere que vous avez saisie est incorrecte !\n");
            System.out.print("Saisir le sexe ('H' pour homme 'F' pour femme et 'U' pour unisexe): ");
            tabsexe[ctr] = sc.nextLine();
        }
        if (codeHomme.equalsIgnoreCase(tabsexe[ctr]))
            tabsexe[ctr] = homme;
        else if (codeFemme.equalsIgnoreCase(tabsexe[ctr]))
            tabsexe[ctr] = femme;
        else if (codeUnisex.equalsIgnoreCase(tabsexe[ctr]))
            tabsexe[ctr] = unisex;
    }
    /*********************************************************************************************************
     cette methode permet la validation de l'origine du vetement,
     si le vetement est fabriqué en interne on demande au user de saisir un numéro égal a 0 et dans ce cas il n'ya pas
     de nom ni de numéro de téléphone a enregistrer on aura 0 comme numero fournisseur et des tirets a la place
     du nom et du du numero de telephone avec une origine local.
     dans le cas contraire s'il est fabriqué en externe on lui demande un numéro superieur a 0
     et c'est ce numero qui sera utilisé comme numero du fournisseur avec une origine externe.
     on enregistre en meme temps son nom et son numéro de telephone avec toutes les validations requises
     et les statut sera actif dans tous les deux cas.
     dans le cas ou le numéro de fournisseur existe déja dans la base on recupere juste
     les informations(nom et numéro de tel) sans le demander a nouveau et on onregistre tout de meme le numéro
     du fournisseur dans deux tableaux de numfourniseur le second nous servira de l'affichage de la liste
     des fournisseurs pour ne pas avoir de doublons.
     *********************************************************************************************************/
    public static void validationOrigineVetement(int ctr, int tabNum[], String message) {
        boolean fin = false;
        String input = "";
         trouve = false;
        ind = 0;
        do {
            System.out.print(message);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else if (!estUnInt(input))
                System.out.println("Valeur incorrecte !");
            else {
                taboriginevetement[ctr] = Integer.parseInt(input);
                numero = taboriginevetement[ctr];
                if ((numero < 0))
                    System.out.println("Le numéro doit être  positif !");
                else {
                    if (numero > 0) {
                        while ((trouve == false) && (ind < ctr)) {

                            if (numero == (tabNum[ind])) {
                                trouve = true;
                            } else {
                                ind++;
                            }
                        }
                        if (trouve) {
                            tabnumfournisseur[ctr] = taboriginevetement[ind];
                            tabnomfournisseur[ctr] = tabnomfournisseur[ind];
                            tabtelephoneFournisseur[ctr] = tabtelephoneFournisseur[ind];
                            taboriginevetement2[ctr] = externe;
                            System.out.println("");
                            System.out.println("Ce fournisseur existe déja dans votre base !");
                            System.out.println("");
                            System.out.println("son nom est: " + tabnomfournisseur[ind]);
                            System.out.println("\nEt son numéro de téléphone Téléphone: " + "("+ tabtelephoneFournisseur[ind].substring(0, 3) + ") "
                                    + tabtelephoneFournisseur[ind].substring(3, 6) + "-"
                                    + tabtelephoneFournisseur[ind].substring(6, 10) + " \n");
                        } else {

                            tabnumfournisseur[ctr] = taboriginevetement[ctr];
                            tabnumfournisseur2[ctr] = taboriginevetement[ctr]; //on enregistre en meme temps sur ce tableau2
                            //pour lors de l'affichage des fournisseurs
                            //on utilise ce tableau pour ne pas avoir de doublon
                            saveNomFournisseur(ctr);
                            saveNumTelephone(ctr);
                            taboriginevetement2[ctr] = externe;
                        }
                    } else {
                        taboriginevetement2[ctr] = interne;
                        tabnomfournisseur[ctr] = tiret;
                        tabtelephoneFournisseur[ctr] = tiret;
                    }
                    tabstatut[ctr] = actif;
                    fin = true;
                }
            }
        } while (!fin);
    }
    /*********************************************************************************************************
     cette methode permet la validation de l'origine du vetement lors du changement  de l'orignine du vetement
     de local en externe
     *********************************************************************************************************/
    public static void validationOrigine(int ctr, int tabNum[], String message) {
        boolean fin = false;
        String input = "";
         trouve = false;
        ind = 0;
        do {
            System.out.print(message);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else if (!estUnInt(input))
                System.out.println("Valeur incorrecte !");
            else {
                taboriginevetement[ctr] = Integer.parseInt(input);
                numero = taboriginevetement[ctr];
                if ((numero <= 0))
                    System.out.println("Le numéro doit être  supérieur à  0 !");
                else {
                    if (numero > 0) {
                        while ((trouve == false) && (ind < ctr)) {

                            if (numero == (tabNum[ind])) {
                                trouve = true;
                            } else {
                                ind++;
                            }
                        }
                        if (trouve) {
                            tabnumfournisseur[ctr] = taboriginevetement[ind];
                            tabnomfournisseur[ctr] = tabnomfournisseur[ind];
                            tabtelephoneFournisseur[ctr] = tabtelephoneFournisseur[ind];
                            taboriginevetement2[ctr] = externe;
                            System.out.println("");
                            System.out.println("Ce fournisseur existe déja dans votre base !");
                            System.out.println("");
                            System.out.println("son nom est: " + tabnomfournisseur[ind]
                                    + " et son numéro de téléphone :" + "("+ tabtelephoneFournisseur[ind].substring(0, 3) + ") "
                                    + tabtelephoneFournisseur[ind].substring(3, 6) + "-"
                                    + tabtelephoneFournisseur[ind].substring(6, 10) + " \n");
                        } else {
                            tabnumfournisseur[ctr] = taboriginevetement[ctr];
                            tabnumfournisseur2[ctr] = taboriginevetement[ctr]; //on enregistre en meme temps sur ce tableau2
                            //pour lors de l'affichage des fournisseurs
                            //on utilise ce tableau pour ne pas avoir de doublon
                            saveNomFournisseur(ctr);
                            saveNumTelephone(ctr);
                            taboriginevetement2[ctr] = externe;
                        }
                    } else {
                        System.out.println("la valeur saisie est incorrecte !");
                    }
                    tabstatut[ctr] = actif;
                    fin = true;
                }
            }
        } while (!fin);
    }
    /*********************************************************************************************************
     cette methode permet de supprimer un vetement
     *********************************************************************************************************/
    public static void deleteRecord(int tabNumero[], int i) {
        tabNumero[i] = tabNumero[i + 1];
        tabdescription[i] = tabdescription[i + 1];
        tabsexe[i] = tabsexe[i + 1];
        tabprixrevient[i] = tabprixrevient[i + 1];
        tabprixvente[i] = tabprixvente[i + 1];
        tabquantite[i] = tabquantite[i + 1];
        tabquantitemin[i] = tabquantitemin[i + 1];
        tabnumfournisseur[i] = tabnumfournisseur[i + 1];
        tabnomfournisseur[i] = tabnomfournisseur[i + 1];
        taboriginevetement[i] = taboriginevetement[i + 1];
        tabtelephoneFournisseur[i] = tabtelephoneFournisseur[i + 1];
        taboriginevetement2[i] = taboriginevetement2[i + 1];
        tabstatut[i] = tabstatut[i + 1];
    }
    /*********************************************************************************************************
     cette fonction permet de verifier si la valeur saisi est un entier
     *********************************************************************************************************/
    public static boolean estUnInt(String entier) {
        boolean v = false;
        try {
            //on essaie de convertir la chaîne en nombre entier
            Integer.parseInt(entier);
            //conversion aboutie, v prend la valeur true
            v = true;
        } catch (Exception e) {
            //conversion échouée, levée d'une exception, v prend false
            v = false;
        }
        //on retourne v
        return v;
    }
    /*********************************************************************************************************
     cette fonction permet de verifier si la valeur saisi est un réel
     *********************************************************************************************************/
    public static boolean estUnDouble(String reel) {
        boolean v = false;
        try {
            //on essaie de convertir la chaîne en nombre réel
            Double.parseDouble(reel);
            //conversion aboutie, v prend la valeur true
            v = true;
        } catch (Exception e) {
            //conversion échouée, levée d'une exception, v prend false
            v = false;
        }
        //on retourne v
        return v;
    }
    /*********************************************************************************************************
     cette methode permet de verifier si la valeur saisi est nulle ou inadequate
     *********************************************************************************************************/
    public static void verifierSaisie() {
        boolean finS = false;

        do {
            System.out.print("\nChoisir une option: ");
            choice = sc.nextLine();
            if (choice.isEmpty()) {
                System.out.println("\nSaisie null !");
            } else if (!estUnInt(choice))
                System.out.println("\nValeur incorrecte !");
            else {
                finS = true;
            }
        } while (!finS);
    }
    /*********************************************************************************************************
     cette fonction permet de verifier si la valeur saisi est nulle ou inadequate et doit accepter uniquement
     les entiers concu spécialement pour les numéros de l'item et ceux des fournisseurs
     *********************************************************************************************************/
    public static int validationNumero(int number, String s) {
        boolean fin = false;
        String input = "";
        do {
            System.out.print(s);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Saisie null !");
            } else if (!estUnInt(input))
                System.out.println("Valeur incorrecte !");
            else {
                numero = Integer.parseInt(input);
                fin = true;
            }
        } while (!fin);
        return number;
    }
    /*********************************************************************************************************
     cette methode permet d'afficher et de modifier des informatios concernant les fournisseurs
     *********************************************************************************************************/
    public static void gestionFournisseur() {
        int choixfournisseur;
        boolean  finlistfounisseur = false;
        while (finlistfounisseur == false) {
            affichermenuFournisseur();
            verifierSaisie();
            choixfournisseur = Integer.parseInt(choice);
            switch (choixfournisseur) {
                case 0:
                    finlistfounisseur = true;
                    break;
                case 1:
                    afficherEnteteFournisseurs();
                    afficherFournisseur();
                    break;
                case 2:
                    detailFournisseur(tabnumfournisseur);
                    break;
                case 3:
                    modifierNomFournisseur(tabnomfournisseur, tabnumfournisseur);
                    break;
                case 4:
                    modifierNumTelephoneFournisseur(tabtelephoneFournisseur, tabnumfournisseur);
                    break;
                default:
                    System.out.println("\nVous avez saisi une option invalide !");
                    break;
            }

        }
    }

}