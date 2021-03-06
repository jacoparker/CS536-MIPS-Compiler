import java.util.*;

/**
 * The Sym class defines a symbol-table entry.
 * Each Sym contains a type (a Type).
 */
public class Sym {
    private Type type;
    private boolean isLocal;
    private int funcOffset;  // only need if isLocal is true

    public Sym(Type type, boolean isLocal, int offset) {
        this.type = type;
        this.isLocal = isLocal;
        this.funcOffset = offset;
    }

    public Sym(Type type) {
        this(type, false, 0);
    }

    public boolean isLocal() {
        return isLocal;
    }

    public int getFuncOffSet() {
        return funcOffset;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return type.toString();
    }
}

/**
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnSym extends Sym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    private int paramSpace;
    private int localSpace;

    public FnSym(Type type, int numparams, int paramSpace, int localSpace) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
        this.paramSpace = paramSpace;
        this.localSpace = localSpace;
    }

    public FnSym(Type type, int numparams) {
        this(type, numparams, 0, 0);
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }

    public int getParamSpace() {
        return paramSpace;
    }

    public int getLocalSpace() {
        return localSpace;
    }

    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public void setParamSpace(int paramSpace) {
        this.paramSpace = paramSpace;
    }

    public void setLocalSpace(int localSpace) {
        this.localSpace = localSpace;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/**
 * The StructSym class is a subclass of the Sym class just for variables
 * declared to be a struct type.
 * Each StructSym contains a symbol table to hold information about its
 * fields.
 */
class StructSym extends Sym {
    // new fields
    private IdNode structType;  // name of the struct type

    public StructSym(IdNode id) {
        super(new StructType(id));
        structType = id;
    }

    public IdNode getStructType() {
        return structType;
    }
}

/**
 * The StructDefSym class is a subclass of the Sym class just for the
 * definition of a struct type.
 * Each StructDefSym contains a symbol table to hold information about its
 * fields.
 */
class StructDefSym extends Sym {
    // new fields
    private SymTable symTab;

    public StructDefSym(SymTable table) {
        super(new StructDefType());
        symTab = table;
    }

    public SymTable getSymTable() {
        return symTab;
    }
}
