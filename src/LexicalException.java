/*
 * Exception for throwing lexical exceptions, for ant brain files or world files when undergoing lexical analysis.
 */
class LexicalException extends Exception {
    public String msg;
    public LexicalException ( String msg_ ) { msg = msg_; }
}