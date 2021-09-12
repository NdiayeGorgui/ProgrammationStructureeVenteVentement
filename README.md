## Mise en situation
A la suite de l’obtention de votre diplôme de programmeur-analyste, un de vos amis qui détient une entreprise de fabrication et de vente de vêtements vous demande de lui développer un programme pour la gestion de son inventaire de vêtements. Il vous remet le présent document pour que vous compreniez bien ce qu’il aimerait que votre programme fasse.

## Les besoins
Afin que vous compreniez bien ses besoins, votre ami propriétaire vous fait la description suivante du fonctionnement de l’entreprise :
« Nous vendons des vêtements. Certains son fabriqués par nous, et d’autres sont fabriqués par quelques créateurs avec qui nous faisons affaires. Certains vêtements sont destinés aux hommes, d’autres aux femmes, et nous avons aussi quelques vêtements unisexes. Pour faciliter notre fonctionnement, nous attribuons un numéro à chaque vêtement; par exemple nous fabriquons quatre pantalons différents pour homme, et leur numéro sont : 1001, 1002, 1010, 1022; nous fabriquons deux types de chemise pour homme qui ont les numéros 1225 et 1256, et ainsi de suite. Chaque vêtement a aussi son propre descriptif : « pantalon ajusté », « pantalon ample », « pantalon court »,  « chemise cintrée », etc. Dans notre inventaire, que nous maintenons présentement dans un fichier Excel, nous indiquons le prix de revient de chaque vêtement (prix de fabrication si nous le faisons nous-mêmes, ou le prix d’achat si c’est un autre créateur qui le fabrique), le prix de vente, la quantité dont nous disposons en inventaire et finalement la quantité minimale que nous devons garder en stock. Lorsqu’un vêtement est fabriqué par un autre créateur, nous l’indiquons également dans le fichier Excel. A noter que notre inventaire conserve la trace de tous les vêtements que nous avons vendu ou fabriqué dans le passé, même si c’est un vêtement qui ne fait plus partie de notre catalogue.

« Nous voulons être en mesure de sortir des listes de notre inventaire. Par exemple :
*	la liste de tous les items que nous produisons ou vendons;
*	la liste des items pour homme, femme ou unisexes;
*	la liste des items dont les stocks sont inférieurs au seuil minimum;
*	la liste des items que nous ne fabriquons ou ne vendons plus.
Nous voulons être en mesure d’ajouter un item dans l’inventaire, d’en supprimer, de modifier un item (ex : modifier la quantité en inventaire ou modifier son prix de revient ou son prix de vente).
Nous voulons connaître la valeur totale de notre inventaire, par rapport au prix de revient des items et par rapport au prix de vente. »

## Mandat
Votre troisième bien livrable consiste à développer une version plus complète de votre programme élaboré dans le cadre du travail pratique #2. Vous devrez :
*	implanter les attributs suivants :
*	nom du fournisseur lorsqu’il s’agit d’un vêtement acheté par l’entreprise (contrairement aux vêtements qu’elle fabrique elle-même);
▪	numéro de téléphone du fournisseur;
◦	implanter la fonctionnalité suivante : permettre la modification de l’indicateur sur la provenance du vêtement (fabriqué versus acheté) et la modification des attributs du fournisseur : nom et téléphone;
*	valider les données saisies par l’utilisateur de telle sorte que votre programme ne plante pas à cause d’une erreur de saisie (ex : l’utilisateur a saisi un caractère alphabétique alors que votre programme s’attendait à un nombre);
*	valider les données saisies par l’utilisateur de telle sorte que les règles d’affaires suivantes soient renforcées :
*	le code de vêtement est composé de chiffres uniquement, et il est unique (pas deux vêtements avec le même code);
*	le descriptif du vêtement commence forcément par une lettre;
*	la quantité et le seuil minimal ne peuvent être négatifs;
*	les prix sont forcément positifs et ne peuvent être supérieurs à $1000.00;
*	le nom du fournisseur est toujours en majuscules;
*	le numéro de téléphone du fournisseur ne peut comporter que des chiffres, le trait-d’union et l’espace;
*	ajouter la possibilité de produire la liste des fournisseurs;
*	ajouter la possibilité de produire la liste des vêtements achetés auprès d’un fournisseur en particulier.

*	Faire en sorte que votre programme soit modulaire : les validations seront faites au moyen de fonctions que vous aurez écrites et les traitements de votre programme seront organisés, quand le contexte s’y prête, en procédures et fonctions.

* Vous devrez également permettre la lecture et la sauvegarde de vos données  dans un fichier. Pour ce faire, vous utiliserez la librairie IOFichierTab qui vous a été fournie
