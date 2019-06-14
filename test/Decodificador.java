
import sun.net.www.http.HttpClient;


/**
 *
 * @author Mikael Bento
 */
public class Decodificador {
    
    public static void main(String args[]) {
        String codificar = "t.este";
        String codificado = "";
        String aux;
        
        codificar.toLowerCase();
        char[] array = codificar.toCharArray();
        
        for (char letra : array) {
            aux = "" + letra;
            if (aux.matches(("[a-z cç] {1}")))
                codificado += (char)(aux.hashCode() + 3);
            else
                codificado += aux;
        }
        System.out.println(codificado);
    }
    public void requicicao(){
//        HttpClient client = new HttpClient();
    }
}
