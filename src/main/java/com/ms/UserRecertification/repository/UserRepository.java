package com.ms.UserRecertification.repository;

import com.ms.UserRecertification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT c.country_name, cc.system, cc.report_profit_center, a.username, first_name, last_name, a.EMAIL_ID, a.werks, " +
            "CASE WHEN kf.company_code_fk IN (22, 23, 27) THEN 'OGA' ELSE 'OFI' END AS OG, " +
            "STRING_AGG(r.role_name, ',') AS roles " +
            "FROM vegauat_manna.users a " +
            "INNER JOIN vegauat_manna.key_finder kf ON a.id = kf.users_fk " +
            "LEFT JOIN vegauat_manna.user_role r ON r.key_finder_fk = kf.id " +
            "INNER JOIN vegauat_manna.plant b ON a.werks = b.plant_id " +
            "INNER JOIN vegauat_manna.country_detail c ON b.country_id = c.country_id " +
            "INNER JOIN vegauat_manna.company_code cc ON kf.company_code_fk = cc.id " +
            "WHERE a.is_active = 1 AND cc.report_profit_center = :reportProfitCenter " +
            "GROUP BY c.country_name, a.username, first_name, last_name, EMAIL_ID, a.werks, OG, cc.system, cc.report_profit_center",
            nativeQuery = true)
    List<User> getUsers(@Param("reportProfitCenter") String reportProfitCenter);

}
