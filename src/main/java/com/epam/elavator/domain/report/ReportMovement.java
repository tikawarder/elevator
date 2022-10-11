package com.epam.elavator.domain.report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportMovement {
    int from;
    int to;
    int loadPeople;
    int unloadPeople;
    Enum State;
}
