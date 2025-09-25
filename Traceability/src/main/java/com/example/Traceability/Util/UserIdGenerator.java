package com.example.Traceability.Util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.List;

public class UserIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "USR-";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            String hql = "SELECT u.id FROM LoginEntity u ORDER BY u.id DESC";

            @SuppressWarnings("unchecked")
            List<String> result = session.createQuery(hql, String.class)
                                         .setMaxResults(1)
                                         .getResultList();

            if (result.isEmpty() || result.get(0) == null) {
                return PREFIX + "0001"; // first user
            }

            String lastId = result.get(0);
            int number = Integer.parseInt(lastId.substring(PREFIX.length()));
            number++;

            return String.format(PREFIX + "%04d", number);

        } catch (Exception e) {
            e.printStackTrace();
            return PREFIX + "0001";
        }
    }
}
