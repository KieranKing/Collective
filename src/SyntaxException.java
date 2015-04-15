
/*
 * Exception for throwing syntax exceptions, for ant brain files or world files when undergoing syntax analysis.
 */
class SyntaxException extends Exception {
    String msg;
    public SyntaxException( String msg_ ) { msg = msg_; }
}