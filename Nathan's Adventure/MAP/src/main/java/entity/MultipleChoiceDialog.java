package entity;

import java.io.Serializable;
import java.util.Scanner;

public class MultipleChoiceDialog implements Serializable{

    private String nameDialog;

    private String opzioneA;
    private String opzioneB;
    private String opzioneC;

    private String rispostaA;
    private String rispostaB;
    private String rispostaC;

    private String subRispostaA;
    private String subRispostaB;
    private String subRispostaC;

    private String subOpzioneA;
    private String subOpzioneB;
    private String subOpzioneC;

    private boolean selectedA = false;
    private boolean selectedB = false;
    private boolean selectedC = false;

    private String endDialog;

    private int caso;

    
    public String getEndDialog() {
        return endDialog;
    }

    public void setEndDialog(String endDialog) {
        this.endDialog = endDialog;
    }

    public String getNameDialog() {
        return nameDialog;
    }

    public void setNameDialog(String nameDialog) {
        this.nameDialog = nameDialog;
    }

    public String getOpzioneA() {
        return opzioneA;
    }

    public void setOpzioneA(String opzioneA) {
        this.opzioneA = opzioneA;
    }

    public String getOpzioneB() {
        return opzioneB;
    }

    public void setOpzioneB(String opzioneB) {
        this.opzioneB = opzioneB;
    }

    public String getOpzioneC() {
        return opzioneC;
    }

    public void setOpzioneC(String opzioneC) {
        this.opzioneC = opzioneC;
    }

    public String getRispostaA() {
        return rispostaA;
    }

    public void setRispostaA(String rispostaA) {
        this.rispostaA = rispostaA;
    }

    public String getRispostaB() {
        return rispostaB;
    }

    public void setRispostaB(String rispostaB) {
        this.rispostaB = rispostaB;
    }

    public String getRispostaC() {
        return rispostaC;
    }

    public void setRispostaC(String rispostaC) {
        this.rispostaC = rispostaC;
    }

    public String getSubRispostaA() {
        return subRispostaA;
    }

    public void setSubRispostaA(String subRispostaA) {
        this.subRispostaA = subRispostaA;
    }

    public String getSubRispostaB() {
        return subRispostaB;
    }

    public void setSubRispostaB(String subRispostaB) {
        this.subRispostaB = subRispostaB;
    }

    public String getSubRispostaC() {
        return subRispostaC;
    }

    public void setSubRispostaC(String subRispostaC) {
        this.subRispostaC = subRispostaC;
    }

    public String getSubOpzioneA() {
        return subOpzioneA;
    }

    public void setSubOpzioneA(String subOpzioneA) {
        this.subOpzioneA = subOpzioneA;
    }

    public String getSubOpzioneB() {
        return subOpzioneB;
    }

    public void setSubOpzioneB(String subOpzioneB) {
        this.subOpzioneB = subOpzioneB;
    }

    public String getSubOpzioneC() {
        return subOpzioneC;
    }

    public void setSubOpzioneC(String subOpzioneC) {
        this.subOpzioneC = subOpzioneC;
    }

    public boolean isSelectedA() {
        return selectedA;
    }

    public void setSelectedA(boolean selectedA) {
        this.selectedA = selectedA;
    }

    public boolean isSelectedB() {
        return selectedB;
    }

    public void setSelectedB(boolean selectedB) {
        this.selectedB = selectedB;
    }

    public boolean isSelectedC() {
        return selectedC;
    }

    public void setSelectedC(boolean selectedC) {
        this.selectedC = selectedC;
    }

    public int getCaso() {
        return caso;
    }

    public void setCaso(int caso) {
        this.caso = caso;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        if (caso == 1) { //CASO 1: a, si dirama in a,b,c (devono essere fatti tutti).

            while (this.isSelectedA() == false) {

                if (this.isSelectedA() == false) {
                    System.out.println("\na: " + this.getOpzioneA() + "\n");
                }

                char scelta = scanner.next().charAt(0);
                scelta = Character.toLowerCase(scelta);

                if (scelta == 'a') {

                    System.out.println("\n" + this.getRispostaA() + "\n");

                    while (this.isSelectedA() == false || this.isSelectedB() == false || this.isSelectedC() == false) {

                        if (this.isSelectedA() == false) {
                            System.out.println("a: " + this.getSubOpzioneA());
                        }
                        if (this.isSelectedB() == false) {
                            System.out.println("b: " + this.getSubOpzioneB());
                        }
                        if (this.isSelectedC() == false) {
                            System.out.println("c: " + this.getSubOpzioneC());
                        }

                        System.out.println();

                        scelta = scanner.next().charAt(0);
                        scelta = Character.toLowerCase(scelta);

                        switch (scelta) {

                            case 'a':
                                if (this.isSelectedA() == false) {
                                    System.out.println("\n" + this.getSubRispostaA() + "\n");
                                    this.setSelectedA(true);
                                } else {
                                    System.out.println("\nScelta non valida: digitare a, b o c.\n");
                                    break;
                                }
                                break;

                            case 'b':
                                if (this.isSelectedB() == false) {
                                    System.out.println("\n" + this.getSubRispostaB() + "\n");
                                    this.setSelectedB(true);
                                } else {
                                    System.out.println("\nScelta non valida: digitare a, b o c.\n");
                                    break;
                                }
                                break;

                            case 'c':
                                if (this.isSelectedC() == false) {
                                    System.out.println("\n" + this.getSubRispostaC() + "\n");
                                    this.setSelectedC(true);
                                } else {
                                    System.out.println("\nScelta non valida: digitare a, b o c.\n");
                                    break;
                                }
                                break;

                            default:
                                System.out.println("\nScelta non valida: digitare a, b o c.\n");
                                break;
                        }

                        if (this.isSelectedA() == true && this.isSelectedB() == true && this.isSelectedC() == true) {
                            System.out.println(this.getEndDialog());
                        }

                    }

                } else {
                    System.out.println("\nScelta non valida: digitare a, b o c.\n");
                }

            }

        }

        if (caso == 2) {   //CASO 2: a,b (devono essere fatti tutti)

            while (this.isSelectedA() == false || this.isSelectedB() == false) {

                if (this.isSelectedA() == false) {
                    System.out.println("a: " + this.getOpzioneA());
                }
                if (this.isSelectedB() == false) {
                    System.out.println("b: " + this.getOpzioneB());
                }

                System.out.println();
                char scelta = scanner.next().charAt(0);
                scelta = Character.toLowerCase(scelta);

                switch (scelta) {
                    case 'a':
                        if (this.isSelectedA() == false) {
                            System.out.println("\n" + this.getRispostaA() + "\n");
                            this.setSelectedA(true);
                        } else {
                            System.out.println("\nScelta non valida: digitare a, b o c.\n");
                            break;
                        }
                        break;

                    case 'b':
                        if (this.isSelectedB() == false) {
                            System.out.println("\n" + this.getRispostaB() + "\n");
                            this.setSelectedB(true);
                        } else {
                            System.out.println("\nScelta non valida: digitare a, b o c.\n");
                            break;
                        }
                        break;

                    default:
                        System.out.println("\nScelta non valida: digitare a, b o c.\n");
                        break;
                }

                if (this.isSelectedA() == true && this.isSelectedB() == true) {
                    System.out.println(this.getEndDialog());
                }

            }

        }// FINE CASO 2

        if (caso == 3) {  //CASO 3: a,b (basta uno per uscire)

            System.out.println("\n" + this.getEndDialog() + "\n");

            while (this.isSelectedA() == false && this.isSelectedB() == false) {

                if (this.isSelectedA() == false) {
                    System.out.println("A: " + this.getOpzioneA());
                }
                if (this.isSelectedB() == false) {
                    System.out.println("B: " + this.getOpzioneB());
                }

                System.out.println();
                char scelta = scanner.next().charAt(0);

                switch (scelta) {
                    case 'a':
                        if (this.isSelectedA() == false) {
                            System.out.println("\n" + this.getRispostaA());
                            this.setSelectedA(true);
                            
                            
                            
                        } else {
                            System.out.println("\nScelta non valida: digitare a, b o c.\n");
                            break;
                        }
                        break;

                    case 'b':
                        if (this.isSelectedB() == false) {
                            System.out.println("\n"+this.getRispostaB());
                            this.setSelectedB(true);
                            
                            
                            
                        } else {
                            System.out.println("\nScelta non valida: digitare a, b o c.\n");
                            break;
                        }
                        break;

                    default:
                        System.out.println("\nScelta non valida: digitare a, b o c.\n");
                        break;
                }

            }

        }//fine caso 3

    }
}
