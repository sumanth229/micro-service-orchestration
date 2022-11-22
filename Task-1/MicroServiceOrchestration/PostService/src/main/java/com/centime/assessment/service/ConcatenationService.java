package com.centime.assessment.service;

import com.centime.assessment.response.Response;

public interface ConcatenationService {
    Response<String> concatName(String name, String surname);
}
