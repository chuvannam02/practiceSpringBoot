package com.test.practiceProject.Entity.Angular;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.practiceProject.Entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Entity.Angular  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:01 AM
 */

@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu extends BaseEntity implements Comparable<Menu>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    private String url;

    private String description;

    private String icon;

    private Integer parentId;
    private String parentName;

    private Integer appId;
    private String appName;

    // Thứ tự sắp xếp
    private Integer order;

    // Với status
    //    1. Hiển thị
    //    0. Không hiển thị
    private Integer status;

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Menu o) {
        // If both objects have order values, compare them
        if (this.order != null && o.getOrder() != null) {
            return this.order.compareTo(o.getOrder());
        }
        // If this object has no order but the other does, this is considered "greater" (comes after)
        else if (this.order == null && o.getOrder() != null) {
            return 1;
        }
        // If this object has order but the other doesn't, this is considered "less" (comes before)
        else if (this.order != null && o.getOrder() == null) {
            return -1;
        }
        // If both objects have no order, compare by ID as a fallback
        else {
            return Integer.compare(this.id, o.getId());
        }
    }
}
