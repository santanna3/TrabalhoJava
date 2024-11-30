

import com.fitlife.utils.DatabaseUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrarUsuario")
public class RegistrarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Receber os parâmetros do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        // Validar senha

        // Inserir os dados no banco
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, senha);

                stmt.executeUpdate();

                // Redirecionar para uma página de sucesso
                response.sendRedirect("cadastro-sucesso.html");
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar no banco de dados.", e);
        }
    }
}
