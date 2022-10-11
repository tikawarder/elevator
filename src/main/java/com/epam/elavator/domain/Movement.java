package com.epam.elavator.domain;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Builder
@Data
public class Movement {
    @Min(0)
    @Max(10)
    int from;

    @Min(0)
    @Max(10)
    int to;

    @Min(1)
    @Max(10)
    int people;
}
