package uk.glasgow.jpmorgan.report.daily.trade;

import java.util.Calendar;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */
public class Instruction implements Comparable<Instruction> {

    private Long instructionId;
    private String entity;
    private String instructionType;
    private Double agreedFX;
    private String currency;
    private Calendar instructionDate;
    private Calendar settlementDate;
    private Long units;
    private Double pricePerUnit;
    private Double tradeAmount;

    public Instruction(Long instructionId, String entity, String instructionType, Double agreedFX, String currency,
                       Calendar instructionDate, Calendar settlementDate, Long units, Double pricePerUnit) {
        this.instructionId = instructionId;
        this.entity = entity;
        this.instructionType = instructionType;
        this.agreedFX = agreedFX;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public int compareTo(Instruction instruction) {
        return instruction.getTradeAmount().compareTo(this.getTradeAmount());
    }

    public Long getInstructionId() {
        return instructionId;
    }

    public void setInstructionIdId(Long instructionId) {
        this.instructionId = instructionId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(String instructionType) {
        this.instructionType = instructionType;
    }

    public Double getAgreedFX() {
        return agreedFX;
    }

    public void setAgreedFX(Double agreedFX) {
        this.agreedFX = agreedFX;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Calendar getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Calendar instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Calendar getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Calendar settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    @Override
    public String toString() {
        return "Instruction [instructionId=" + instructionId + ", entity=" + entity + ", tradeAmount=" + tradeAmount
            + ", instructionType=" + instructionType + ", agreedFX=" + agreedFX + ", currency=" + currency
            + ", instructionDate=" + instructionDate.getTime() + ", settlementDate=" + settlementDate.getTime()
            + ", units=" + units + ", pricePerUnit=" + pricePerUnit + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instruction other = (Instruction) obj;
        if (agreedFX == null) {
            if (other.agreedFX != null)
                return false;
        } else if (!agreedFX.equals(other.agreedFX))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        if (instructionDate == null) {
            if (other.instructionDate != null)
                return false;
        } else if (!instructionDate.equals(other.instructionDate))
            return false;
        if (instructionId == null) {
            if (other.instructionId != null)
                return false;
        } else if (!instructionId.equals(other.instructionId))
            return false;
        if (instructionType == null) {
            if (other.instructionType != null)
                return false;
        } else if (!instructionType.equals(other.instructionType))
            return false;
        if (pricePerUnit == null) {
            if (other.pricePerUnit != null)
                return false;
        } else if (!pricePerUnit.equals(other.pricePerUnit))
            return false;
        if (settlementDate == null) {
            if (other.settlementDate != null)
                return false;
        } else if (!settlementDate.equals(other.settlementDate))
            return false;
        if (tradeAmount == null) {
            if (other.tradeAmount != null)
                return false;
        } else if (!tradeAmount.equals(other.tradeAmount))
            return false;
        if (units == null) {
            if (other.units != null)
                return false;
        } else if (!units.equals(other.units))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agreedFX == null) ? 0 : agreedFX.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((instructionDate == null) ? 0 : instructionDate.hashCode());
        result = prime * result + ((instructionId == null) ? 0 : instructionId.hashCode());
        result = prime * result + ((instructionType == null) ? 0 : instructionType.hashCode());
        result = prime * result + ((pricePerUnit == null) ? 0 : pricePerUnit.hashCode());
        result = prime * result + ((settlementDate == null) ? 0 : settlementDate.hashCode());
        result = prime * result + ((tradeAmount == null) ? 0 : tradeAmount.hashCode());
        result = prime * result + ((units == null) ? 0 : units.hashCode());
        return result;
    }

}
