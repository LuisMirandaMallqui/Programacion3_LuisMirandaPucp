package pe.edu.pucp.transitsoft.bo.mappers;

/**
 *
 * @author eric
 */
public interface Mapper<S, T> {
    T map(S original);
}
