/**
 *
 * @author Mikael Bento
 */
public class Decodificador {
    
    public static void main(String args[]) {
        String codificar = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";
        String codificado = "";
        String aux;
        
        char[] array = codificar.toCharArray();
        
        for (char letra : array) {
            aux = "" + letra;
            if (aux.matches(("[a-z cç] {0}")))
                codificado += (char)(aux.hashCode() - 3);
            else
                codificado += aux;
        }
        System.out.println(codificado);
    }
    public void requicicao(){
//        HttpClient client = new HttpClient();
    }
}
