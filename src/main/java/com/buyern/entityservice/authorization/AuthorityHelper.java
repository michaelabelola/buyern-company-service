package com.buyern.entityservice.authorization;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Objects;

@Data
public class AuthorityHelper {
    /**
     * <h3>Check if authority exists in list</h3>
     *
     * @param authorityList {@link List} of {@link GrantedAuthority} to check against
     * @param authority     authority to be checked
     * @return <b><code>true</code></b> if authority exists in list
     * else returns <b><code>false</code></b> {@link Boolean}
     */
    public boolean exists(List<GrantedAuthority> authorityList, String authority) {
        for (GrantedAuthority grantedAuthority : authorityList) {
            if (Objects.equals(grantedAuthority.getAuthority(), authority))
                return true;
        }
        return false;
    }
}

