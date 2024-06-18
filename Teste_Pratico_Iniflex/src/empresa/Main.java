package empresa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();
		// 3.1 - Inserir todos os funcionários
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios
				.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios
				.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		// 3.2 - Remover o funcionário "João"
		funcionarios.removeIf(f -> f.getNome().equals("João"));

		// 3.3 - Imprimir todos os funcionários
		System.out.println("Lista de Funcionários:");
		funcionarios.forEach(System.out::println);

		// 3.4 - Aumentar salário em 10%
		funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

		// 3.5 - Agrupar funcionários por função
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		// 3.6 - Imprimir funcionários agrupados por função
		System.out.println("\nFuncionários agrupados por função:");
		funcionariosPorFuncao.forEach((funcao, lista) -> {
			System.out.println(funcao + ":");
			lista.forEach(System.out::println);
		});

		// 3.8 - Imprimir funcionários que fazem aniversário em outubro e dezembro
		System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
		funcionarios.stream().filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER
				|| f.getDataNascimento().getMonth() == Month.DECEMBER).forEach(System.out::println);

		// 3.9 - Imprimir o funcionário com a maior idade
		Funcionario maisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento))
				.orElseThrow(() -> new IllegalArgumentException("Lista de funcionários está vazia"));

		System.out.println("\nFuncionário com maior idade: " + maisVelho.getNome() + ", Idade: "
				+ (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()) + " anos");

		// 3.10 - Imprimir lista de funcionários por ordem alfabética
		System.out.println("\nFuncionários em ordem alfabética:");
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(System.out::println);

		// 3.11 - Imprimir o total dos salários
		BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		System.out.println("\nTotal dos salários: "
				+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalSalarios));

		// 3.12 - Imprimir quantos salários mínimos cada funcionário ganha
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		System.out.println("\nQuantidade de salários mínimos por funcionário:");
		funcionarios.forEach(f -> {
			BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
			System.out.println(f.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
		});
	}
}