package assessoria.repository.pessoaRepository;

import assessoria.model.entidades.Pessoa;

import java.util.Map;
import java.util.Optional;

public interface PessoaRepository<T extends Pessoa> {

    Optional<T> findById(String id);
    Optional<T> findByEmail(String email);
    Optional<T> add(T pessoa);
    Map<String, T> getAll();
    void remove(T pessoa);
    void save();

}
