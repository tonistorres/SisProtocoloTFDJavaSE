package br.com.subgerentepro.metodosstatics;

import java.awt.Component;
import java.text.Normalizer;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

//********************************************************************************************//
//AULA IMPORTANTÍSSIMA COM DR NÉLIO ALVES EXPLICANDO MÉTODOS static                          //
//******************************************************************************************//
//MEMBROS STATIC PARTE 1                                                                   //
//Udemy:https://www.udemy.com/java-curso-completo/learn/v4/t/lecture/10566010?start=0     //     
//MEMBROS STATIC PARTE 2                                                                 //
//Udemy:https://www.udemy.com/java-curso-completo/learn/v4/t/lecture/10566014?start=0   //
//*************************************************************************************//
public class MetodoStaticosUtil {

    /**
     * Aqui nó temos método static que tem por finalidade tirar todos os acentos
     * e caracteres especiais de um campo do tipo String Youtube Loiane Groner:
     * https://www.youtube.com/watch?v=Dz_w4YpFL80
     */
    public static String removerAcentosCaixAlta(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

     public static String removeEspacosMatemEspacoEntrePalavras(String str){
    return str.replaceAll("\\s+", " ").trim();
    }
    
    public static String retirarTodosEspacosEmBranco(String str){
    
        return str.replaceAll(" ", "");
    }
    
    
    /**
     * Para utilização da função pegue o campo do formulario por meio de uma
     * string e coloque a string que capturou o que foi digitado no campo e
     * adicione na função Ex: String str=txtEstado.getText();
     */
    public static boolean verificarDigitacaoUF(String str) {

        boolean ehvalido = false;

        if (str.equalsIgnoreCase("ACRE")) {

            ehvalido = true;

            return ehvalido;
        }

        if (str.equalsIgnoreCase("ALAGOAS")) {

            ehvalido = true;

            return ehvalido;

        }

        if (str.equalsIgnoreCase("amapa")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Amazonas")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Bahia")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Ceara")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Distrito Federal")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Espirito Santo")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Goias")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Maranhao")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Mato Grosso")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Mato Grosso do Sul")) {

            ehvalido = true;

            return ehvalido;

        }

        if (str.equalsIgnoreCase("Minas Gerais")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Para")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Paraiba")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Parana")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Pernambuco")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Piaui")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Rio de Janeiro")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Rio Grande do Norte")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Rio Grande do Sul")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Rondonia")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Roraima")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Santa Catarina")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Sao Paulo")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Sergipe")) {

            ehvalido = true;

            return ehvalido;

        }
        if (str.equalsIgnoreCase("Tocantins")) {

            ehvalido = true;

            return ehvalido;

        }
        return ehvalido;
    }

    public static boolean isCPF(String CPF) {

        /**
         * Verifia se Algo foi digitado no campo CPF pelo usuário
         */
        if (CPF == null || CPF.equals("")) {
//            JOptionPane.showMessageDialog(null, "CONDIÇÃO:\n"
//                    + "O Sistema Detectou que não há inserção de Dados no Campo CPF\n"
//                    + "OBSERVAÇÃO:\n"
//                    + "Campo Obrigatório.");
            return (false);
        }

        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {

//            JOptionPane.showMessageDialog(null, "CONDIÇÕES:\n"
//                    + "1ª) O CPF  digitado possui sequência de Número IGUAIS;\n "
//                    + "2ª) Número de dígitos digitados é DIFERENTE de 11char.\n"
//                    + "OBSERVAÇÃO:\nDigite o CPF Corretamente campo Obrigatório.");
            return (false);

        }

        char dig10, dig11;
        int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0         
// (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

// Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
              //  JOptionPane.showMessageDialog(null, "PARABÉNS:\n Número validado com Sucesso!!");
                return (true);
            } else {
                //JOptionPane.showMessageDialog(null, "Os Digitos Calculados NÃO confere com os digitos Informados");
                return (false);
            }
        } catch (InputMismatchException erro) {
          //  JOptionPane.showMessageDialog(null, "Erro Capturado:\n" + erro.getMessage() + "\nPorfavor tire um Print e Entrar em Contado com o Desenvolvedor do Sistema\nAnalista:Tonis A. Torres Ferreira\nsisvenda2011@gmail.com");
            return (false);
        }
    }

    /**
     * Esse método tem por finalidade tirar a mascara da string digitada pelo
     * usuário no campo CNPJ para lançar dentro método isCNPJ sem mascara.
     */
    public static String trataMaskParaCNPJ(String str) {

        int cont = 0;

        //inicia-se o for que irá percorrer o tamanho total da variável mensagem que guarda o valor capturado do campo txtAgencia
        for (int i = 0; i < str.length(); i++) {

            cont += 1;//Aqui o contador começa a ser incrementado em mais um a cada passada 

            //Quando o contador estiver na posicao 5 execute o codigo abaixo 
            if (cont == 3) {
                if (str.charAt(i) == '.') {

                    /**
                     * o método replace efetua essa mudança de comportamento
                     * nesta String
                     */
                    str = str.replace(str.charAt(i), ' ');

                }
            }

            //Quando o contador estiver na posicao 5 execute o codigo abaixo 
            if (cont == 7) {
                if (str.charAt(i) == '.') {

                    /**
                     * o método replace efetua essa mudança de comportamento
                     * nesta String
                     */
                    str = str.replace(str.charAt(i), ' ');

                }
            }

            //Quando o contador estiver na posicao 5 execute o codigo abaixo 
            if (cont == 11) {
                if (str.charAt(i) == '/') {

                    /**
                     * o método replace efetua essa mudança de comportamento
                     * nesta String
                     */
                    str = str.replace(str.charAt(i), ' ');

                }
            }

            //Quando o contador estiver na posicao 5 execute o codigo abaixo 
            if (cont == 16) {
                if (str.charAt(i) == '-') {

                    /**
                     * o método replace efetua essa mudança de comportamento
                     * nesta String
                     */
                    str = str.replace(str.charAt(i), ' ');

                }
            }
        }
        return str.replace(" ", "");// depois de tirar a mascara retorna a sting com os espaços tirados 

    }

    public static boolean isCNPJ(String CNPJ) {

// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static void addMensagem(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg);
    }

    
    
}
