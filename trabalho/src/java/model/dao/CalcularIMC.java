import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcularIMC")
public class CalcularIMC extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Obtém os valores enviados pelo formulário
        String alturaStr = request.getParameter("altura");
        String pesoStr = request.getParameter("peso");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Resultado do IMC</title></head>");
            out.println("<body style='font-family: Arial, sans-serif; text-align: center;'>");
            out.println("<h1>Resultado do IMC</h1>");

            try {
                // Converte os valores para números
                double altura = Double.parseDouble(alturaStr);
                double peso = Double.parseDouble(pesoStr);

                if (altura <= 0 || peso <= 0) {
                    throw new NumberFormatException("Valores inválidos");
                }

                // Calcula o IMC
                double imc = peso / (altura * altura);
                String classificacao;

                if (imc < 18.5) classificacao = "Abaixo do peso";
                else if (imc < 24.9) classificacao = "Peso normal";
                else if (imc < 29.9) classificacao = "Sobrepeso";
                else classificacao = "Obesidade";

                // Exibe o resultado
                out.printf("<p>Seu IMC é <strong>%.2f</strong> (%s)</p>", imc, classificacao);

            } catch (NumberFormatException e) {
                out.println("<p style='color: red;'>Erro: Por favor, insira valores numéricos válidos!</p>");
            }

            out.println("<a href='index.html'>Voltar para a Página Inicial</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
