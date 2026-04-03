class Customer {
  private String name;
  private int age;
  private String AadharNumber;
  private String ContactNumber;

  Customer(String name, int age, String AadharNumber, String ContactNumber) {
    this.name = name;
    this.age = age;
    this.AadharNumber = AadharNumber;
    this.ContactNumber = ContactNumber;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getAadhar() {
    return AadharNumber;
  }

  public String getContact() {
    return ContactNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Customer))
      return false;
    Customer customer = (Customer) o;
    return AadharNumber.equals(customer.AadharNumber);
  }

  @Override
  public int hashCode() {
    return AadharNumber.hashCode();
  }
}
