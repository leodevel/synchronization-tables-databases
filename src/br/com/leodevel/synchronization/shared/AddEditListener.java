package br.com.leodevel.synchronization.shared;

public interface AddEditListener<M> {
    
    public void insert(M m);
    
    public void update(M to, M from);
    
}
