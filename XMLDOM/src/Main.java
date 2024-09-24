public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");

    }
}

DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();

Document document = builder.parse(new File(RUTA));