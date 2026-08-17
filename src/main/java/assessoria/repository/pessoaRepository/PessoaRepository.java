package assessoria.repository.pessoaRepository;

import assessoria.model.entidades.Pessoa;

import java.util.Map;
import java.util.Optional;

public interface PessoaRepository<T extends Pessoa> {

    Optional<T> findById(String id);
    Optional<T> findByEmail(String email);
    T add(T pessoa);
    Map<String, T> getAll();
    boolean existsByEmail(String email);
    boolean existsByEmail(String email, String idIgnorar);
    boolean existsByCpf(String cpf);
    boolean existsByCpf(String cpf, String idIgnorar);
    void remove(T pessoa);
    void save();

}
