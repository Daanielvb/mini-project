package com.example.demo.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ProblemDetail;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class CustomProblemDetail extends ProblemDetail {

    public Collection<String> errors;

    public CustomProblemDetail(ProblemDetail detail, Collection<String> errors){
        super(detail);
        this.errors = errors;
    }
}

