package br.projetosuniso.minebank.api.Service;

import br.projetosuniso.minebank.api.Model.Conta;
import br.projetosuniso.minebank.api.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository _cr;

    public void adicionarConta(Conta conta) {
        _cr.save(conta);
    }

    public List<Conta> listarContas() {
        return _cr.findAll();
    }

    public Optional<Conta> obterPorCpf(String cpf) {
        return _cr.findByCpf(cpf);
    }

    public Optional<Conta> obterPorId(Long id) {
        return _cr.findById(id);
    }

    public void atualizarConta(Conta contaAtual, Conta novaConta) {

        contaAtual.setNumero(novaConta.getNumero());
        contaAtual.setAgencia(novaConta.getAgencia());
        contaAtual.setSenha(novaConta.getSenha());

        _cr.save(contaAtual);
    }

    public void deletarConta(Conta conta) {
        _cr.delete(conta);
    }

    public Integer verificaContaExiste(String cpf, Long senha) {
        return _cr.verifyContaExists(cpf, senha);
    }

    public void atualizarSaldo(Conta conta, Long saldo) {
        conta.setSaldo(saldo);
        _cr.save(conta);
    }

    public void atualizarPoupanca(Conta conta, Long poupanca) {
        conta.setPoupanca(poupanca);
        _cr.save(conta);
    }
}