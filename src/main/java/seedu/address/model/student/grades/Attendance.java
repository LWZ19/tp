package seedu.address.model.student.grades;

/**
 * Represents the attendance for a specific tutorial slot.
 */
public class Attendance {

    private boolean isPresent;

    public Attendance() {
        isPresent = false;
    }

    public void mark() {
        isPresent = true;
    }

    public void unmark() {
        isPresent = false;
    }

    public boolean getIsPresent() {
        return isPresent;
    }

    @Override
    public String toString() {
        return isPresent ? "Present" : "Absent";
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return isPresent == otherAttendance.isPresent;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isPresent);
    }
}
