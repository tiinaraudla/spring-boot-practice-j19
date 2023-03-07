package com.sda.study.springbootpractice.handlers;

import com.sda.study.springbootpractice.utils.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * custom handler to implement AuditAware
 * @author Tiina Raudla
 * @Date
 */
public class AuditAwareHandler implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of(Constants.Audit.DEFAULT_AUDITOR);
    }
}
