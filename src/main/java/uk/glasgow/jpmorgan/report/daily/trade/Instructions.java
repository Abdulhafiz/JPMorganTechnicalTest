package uk.glasgow.jpmorgan.report.daily.trade;

import java.util.ArrayList;
import java.util.List;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */
public class Instructions {
    private List<Instruction> outgoingBuyingInstructions;
    private List<Instruction> incomingSellingInstructions;

    public List<Instruction> getOutgoingBuyingInstructions() {
        if (outgoingBuyingInstructions == null) {
            outgoingBuyingInstructions = new ArrayList<Instruction>();
        }
        return outgoingBuyingInstructions;
    }

    public void setOutgoingBuyingInstructions(List<Instruction> outgoingBuyingInstructions) {
        this.outgoingBuyingInstructions = outgoingBuyingInstructions;
    }

    public List<Instruction> getIncomingSellingInstructions() {
        if (incomingSellingInstructions == null) {
            incomingSellingInstructions = new ArrayList<Instruction>();
        }
        return incomingSellingInstructions;
    }

    public void setIncomingSellingInstructions(List<Instruction> incomingSellingInstructions) {
        this.incomingSellingInstructions = incomingSellingInstructions;
    }
}
