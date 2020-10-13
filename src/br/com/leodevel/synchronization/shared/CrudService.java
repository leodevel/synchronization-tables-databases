package br.com.leodevel.synchronization.shared;

import br.com.leodevel.synchronization.models.Column;
import br.com.leodevel.synchronization.models.Entity;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CrudService<M> {

    public int getPosition(List<M> list, M obj) {
        return 0;
    }

    public List<Column> getColumns() {
        return Arrays.asList();
    }

    public Entity getEntity() throws Exception {
        return (Entity) ((Class) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
    }

    public List<M> getAll() throws Exception {

        Entity entity = getEntity();

        createFileCofig(entity.getFilename(), "<list/>");

        List<M> list = (List<M>) entity.getStream()
                .fromXML(Paths.get(entity.getFilename()).toFile());

        return list;

    }

    public M get() throws Exception {

        Entity entity = getEntity();

        createFileCofig(entity.getFilename(), "<" + entity.getAlias() + "/>");

        M obj = (M) entity.getStream().fromXML(Paths.get(entity.getFilename()).toFile());

        return obj;
    }

    public void update(M to, M from) throws Exception {
        
        List<M> list = getAll();
        
        list.set(getPosition(list, to), from);

        save(list);

    }

    public void update(M obj) throws Exception {
        
        Entity entity = (Entity) obj;

        createFileCofig(entity.getFilename(), "<" + entity.getAlias() + "/>");
        
        String xml = entity.toXML();        
        
        Files.write(Paths.get(entity.getFilename()), new ArrayList<>(Arrays.asList(xml)),
                StandardCharsets.UTF_8);

    }

    public void delete(M obj) throws Exception {

        List<M> list = getAll();
        list.remove(getPosition(list, obj));

        save(list);

    }

    public void insert(M obj) throws Exception {

        List<M> list = getAll();
        list.add(obj);

        save(list);

    }

    public void save(List<M> list) throws Exception {

        Entity entity = getEntity();

        String xml = entity.getStream().toXML(list);

        createFileCofig(entity.getFilename(), "");

        Files.write(Paths.get(entity.getFilename()), new ArrayList<>(Arrays.asList(xml)),
                StandardCharsets.UTF_8);

    }

    private void createFileCofig(String filename, String text) {
        File fileConfig = Paths.get(filename).toFile();

        if (!fileConfig.getParentFile().exists()) {
            fileConfig.getParentFile().mkdirs();
        }

        if (!fileConfig.exists()) {
            try {
                fileConfig.createNewFile();
                Files.write(fileConfig.toPath(), text.getBytes(),
                        StandardOpenOption.WRITE);
            } catch (IOException ex1) {
            }
        }
    }

}
