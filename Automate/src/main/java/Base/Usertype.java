package Base;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Usertype{
	String[] value();
}