### Description

Non intern earnings income = somme des declared earnings somme des gains\
declared =salary, overtime, bonus, vacation pay  
déclaré = salaire,Heures supplémentaires, Bonus, Prime de vacances\
undeclared=stock option,benefits,vouchers
Pas déclaré = option de stock, bénéfices, bons offerts\
Basis = somme income
income < 2.5*minmum wage\
Min wage = 1014,00 revenu < 2.5*salaire minimum
CICE=Basis*6%


L'énoncé du problème traitait du calcul du Crédit d'Impôpour la Compétitivité et l'Emploi (CICE) pour un ensemble d'employés dans une
entreprise.

Chaque employé dispose d'un ensemble de gains qui sont classés en plusieurs types : salaire, heures supplémentaires, bonus, congés payés, options d'achat d'actions, avantages et bons. 

Parmi ces types, seuls le salaire, les heures supplémentaires, lebonus et les congés payés sont considérés comme des gains déclarés.

Le CICE est calculé sur la base de ces gains déclarés poules employés non-stagiaires. Plus précisément, pour chaque employé non-stagiaire, la somme de leurs gains déclarés est calculée.

Ensuite, si le revenu total est inférieur à 2,5 fois le salaire minimu(qui est fixé à 1014 euros dans cet exemple), le CICE est calculé comme étant 6% de ce revenu total.

Cependant, le problème initial était que le test unitaire pour cette fonctionnalité échouait car le résultat attend(126) ne correspondait pas au résultat actuel calculé par le service.

Après avoir corrigé les données de test pour que le total des revenus déclarés soit égal à 2100 (au lieu de 2200), la fonction de calcul du CICE a correctement renvoyé la valeur attendue de 126.
Ainsi, le problème a été résolu en assurant que les données de test correspondent aux exigences de l'énoncé du problème.