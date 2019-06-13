
import sun.net.www.http.HttpClient;


/**
 *
 * @author Mikael Bento
 */
public class Decodificador {
    
    public static void main(String args[]) {
        String codificar = "teste";
        codificar.toLowerCase();
        
        String codificado = "";
        String aux = "";
       
        char[] array = codificar.toCharArray();
        for (char letra : array) {
            aux = "" + letra;
            if (aux.matches(("[a-z cç]{" + aux.length() + "}")))
                codificado += (char)(aux.hashCode() + 3);
            else
                codificado += aux;
        }
        System.out.println(codificado);
    }
    public void requicicao(){
        HttpClient client = new HttpClient();
    }
}
