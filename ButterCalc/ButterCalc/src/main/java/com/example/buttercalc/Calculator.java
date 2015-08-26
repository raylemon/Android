package com.example.buttercalc;
/**
 * Created by Cedric on 8/03/14.
 * Modèle de l'objet.
 * Effectue des calculs de base.
 */

import java.util.Observable;

public class Calculator extends Observable {
    private double result;
    private String operator;
    private String input; //récupère la donnée en texte

    /**
     * Constructeur
     * Initialise les champs
     */
    public Calculator() {
        reset();
    }

    /**
     * Setter. Stocke l'opérande
     *
     * @param input le caractère tapé
     */
    public void setInput(String input) {
        this.input += input;
        if (this.input.startsWith("0")) this.input = this.input.substring(1);
        setChanged();
        notifyObservers(this.input); //on notifie les observers en passant la nouvelle valeur
    }

    /**
     * Setter. Stocke l'opération à  effectuer.
     * Calcule les données précédentes si nécessaire et nofitie l'observer du changement
     *
     * @param operator l'opération à  effectuer
     */
    public void setOperator(String operator) {
        compute();
        this.operator = operator;
        if (!operator.equals("=")) this.input = ""; //on reset l'opérande pour éviter les problèmes
        setChanged();
        notifyObservers(String.valueOf(this.result)); //on notifie le changement de résultat
    }

    /**
     * Getter. Retourne le résultat du calcul:
     * Notifie aussi l'observer
     *
     * @return le résultat:
     */
    public double getResult() {
        compute(); //on force le calcul
        setChanged();
        notifyObservers(String.valueOf(this.result));
        return this.result;
    }

    /**
     * Effectue le calcul en interne et met à  jour les champs.
     */
    private void compute() {
        double i = Double.parseDouble(this.input);
        if (this.operator.equals(""))
            this.result = i; //si pas d'opérateur, on stocke le nombre saisi
        else {
            if (this.operator.equals("+")) this.result += i; //on additionne
            if (this.operator.equals("-")) this.result -= i; //on soustrait
            if (this.operator.equals("*")) this.result *= i; //on multiplie
            if (this.operator.equals("/")) {
                try {
                    this.result /= i; //on tente de diviser
                } catch (ArithmeticException ae) {
                    result = 0;
                }
            }
        }
        this.input = ""; //reset de l'opérande
    }

    /**
     * Remet tous les champs à  0;
     */
    public void reset() {
        this.result = 0;
        this.input = "0";
        this.operator = "";
    }

    /**
     * Supprime le dernier caractère erronné;
     */
    public void undo() {
        this.input = this.input.substring(0, this.input.length() - 1);
        setChanged();
        notifyObservers(this.input);
    }
}
