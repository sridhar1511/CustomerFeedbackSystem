package App;
import java.util.Scanner;
import DAO.CustomerDAO;
import DAO.FeedbackDAO;
import DAO.ProductDAO;
import DB.DBConnection;
import Entity.Customer;
import Entity.Feedback;
import Entity.Product;
public class MainApp
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        DBConnection.getConnection();
        System.out.println("Welcome to the Customer Feedback System");

        boolean running = true;

        while (running) {
            System.out.println("\n==== Customer Feedback System ====");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (mainChoice) {
                case 1 -> {
                    System.out.print("Enter admin password: ");
                    String adminPass = sc.nextLine();
                    if (!adminPass.equals("Sridhar")) {
                        System.out.println("Invalid password. Enter correct password.");
                    } else {
                        boolean adminRunning = true;
                        while (adminRunning) {
                            System.out.println("\n--- Admin Menu ---");
                            System.out.println("1. Add Product");
                            System.out.println("2. Update Product Category");
                            System.out.println("3. Delete Product");
                            System.out.println("4. View All Feedback");
                            System.out.println("5. Reply to Feedback");
                            System.out.println("6. Exit Admin Panel");
                            System.out.print("Enter your choice: ");
                            int ch = sc.nextInt();
                            sc.nextLine(); // consume newline

                            switch (ch) {
                                case 1 -> {
                                    System.out.print("Enter product name: ");
                                    String pname = sc.nextLine();
                                    System.out.print("Enter category: ");
                                    String pcat = sc.nextLine();
                                    productDAO.addProduct(new Product(0, pcat, pname));
                                }
                                case 2 -> {
                                    System.out.print("Enter product ID: ");
                                    int pid = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter new category: ");
                                    String newCat = sc.nextLine();
                                    productDAO.updateProductCategory(pid, newCat);
                                }
                                case 3 -> {
                                    System.out.print("Enter product ID: ");
                                    int pid = sc.nextInt();
                                    productDAO.deleteProduct(pid);
                                }
                                case 4 -> feedbackDAO.viewAllFeedback();
                                case 5 -> {
                                    System.out.print("Enter feedback ID: ");
                                    int fid = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter reply message: ");
                                    String reply = sc.nextLine();
                                    feedbackDAO.replyToFeedback(fid, reply);
                                }
                                case 6 -> {
                                    System.out.println("Exiting Admin Panel.");
                                    adminRunning = false;
                                }
                                default -> System.out.println("Invalid choice. Try again.");
                            }
                        }
                    }
                }

                case 2 -> {
                    boolean customerRunning = true;
                    while (customerRunning) {
                        System.out.println("\n--- Customer Menu ---");
                        System.out.println("1. Add Customer");
                        System.out.println("2. Update Customer Email");
                        System.out.println("3. Delete Customer");
                        System.out.println("4. Give Feedback");
                        System.out.println("5. Update Feedback");
                        System.out.println("6. Delete Feedback");
                        System.out.println("7. Exit Customer Panel");
                        System.out.print("Enter your choice: ");
                        int ch = sc.nextInt();
                        sc.nextLine(); // consume newline

                        switch (ch) {
                            case 1 -> {
                                System.out.print("Enter name: ");
                                String cname = sc.nextLine();
                                System.out.print("Enter email: ");
                                String cemail = sc.nextLine();
                                customerDAO.addCustomer(new Customer(0, cname, cemail));
                            }
                            case 2 -> {
                                System.out.print("Enter customer ID: ");
                                int cid = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter new email: ");
                                String newEmail = sc.nextLine();
                                customerDAO.updateCustomerEmail(cid, newEmail);
                            }
                            case 3 -> {
                                System.out.print("Enter customer ID: ");
                                int cid = sc.nextInt();
                                customerDAO.deleteCustomer(cid);
                            }
                            case 4 -> {
                                System.out.print("Enter your Customer ID: ");
                                int cid = sc.nextInt();
                                System.out.print("Enter Product ID: ");
                                int pid = sc.nextInt();
                                System.out.print("Enter rating (1–5): ");
                                int rating = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter comments: ");
                                String comment = sc.nextLine();
                                feedbackDAO.addFeedback(new Feedback(cid, pid, rating, comment));
                            }
                            case 5 -> {
                                System.out.print("Enter feedback ID: ");
                                int fid = sc.nextInt();
                                System.out.print("Enter new rating: ");
                                int newRating = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter new comment: ");
                                String newComment = sc.nextLine();
                                feedbackDAO.updateFeedback(fid, newRating, newComment);
                            }
                            case 6 -> {
                                System.out.print("Enter feedback ID: ");
                                int fid = sc.nextInt();
                                feedbackDAO.deleteFeedback(fid);
                            }
                            case 7 -> {
                                System.out.println("Exiting Customer Panel.");
                                customerRunning = false;
                            }
                            default -> System.out.println("Invalid choice. Try again.");
                        }
                    }
                }

                case 3 -> {
                    System.out.println("Thanks for your time!");
                    running = false;
                    sc.close();
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

}

