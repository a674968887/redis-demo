package redis.cache.user.pojo;


import java.io.Serializable;
import java.util.Objects;

/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/4/21 16:44 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
public class User implements Serializable {
    private static final long serialVersionUID = -1L;
    private String id;
    private String name;
    private String addrs;


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addrs='" + addrs + '\'' +
                '}';
    }

    public User(String id, String name, String addrs) {
        this.id = id;
        this.name = name;
        this.addrs = addrs;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(addrs, user.addrs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addrs);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }
}
