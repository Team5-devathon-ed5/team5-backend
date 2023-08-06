package com.team5devathon5.abledappbackend.accounts;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    LODGER("ROLE_LODGER"),
    HIRER("ROLE_HIRER"),
    LODGER_AND_HIRER("ROLE_LODGER_AND_HIRER");
    private final String roleName;

    Role(String roleName){
        this.roleName=roleName;
    }
    public String getRoleName(){
        return roleName;
    }
}
