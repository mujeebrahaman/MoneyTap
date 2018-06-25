package demo.android.moneytap.com.moneytap.framework.response;

import java.util.ArrayList;

import demo.android.moneytap.com.moneytap.framework.model.Model;

/*To get content data or details*/

public class GetContentData implements Model {

    private boolean batchcomplete;

    public boolean getBatchcomplete() {
        return this.batchcomplete;
    }

    public void setBatchcomplete(boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    private Query query;

    public Query getQuery() {
        return this.query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public class Query {
        private ArrayList<Page> pages;

        public ArrayList<Page> getPages() {
            return this.pages;
        }

        public void setPages(ArrayList<Page> pages) {
            this.pages = pages;
        }

        public class Page {
            private int pageid;

            public int getPageid() {
                return this.pageid;
            }

            public void setPageid(int pageid) {
                this.pageid = pageid;
            }

            private int ns;

            public int getNs() {
                return this.ns;
            }

            public void setNs(int ns) {
                this.ns = ns;
            }

            private String title;

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private Thumbnail thumbnail;

            public Thumbnail getThumbnail() {
                return this.thumbnail;
            }

            public void setThumbnail(Thumbnail thumbnail) {
                this.thumbnail = thumbnail;
            }

            private String pageimage;

            public String getPageimage() {
                return this.pageimage;
            }

            public void setPageimage(String pageimage) {
                this.pageimage = pageimage;
            }

            private Terms terms;

            public Terms getTerms() {
                return this.terms;
            }

            public void setTerms(Terms terms) {
                this.terms = terms;
            }

            public class Thumbnail {
                private String source;

                public String getSource() {
                    return this.source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                private int width;

                public int getWidth() {
                    return this.width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                private int height;

                public int getHeight() {
                    return this.height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public class Terms {
                private ArrayList<String> alias;

                public ArrayList<String> getAlias() {
                    return this.alias;
                }

                public void setAlias(ArrayList<String> alias) {
                    this.alias = alias;
                }

                private ArrayList<String> description;

                public ArrayList<String> getDescription() {
                    return this.description;
                }

                public void setDescription(ArrayList<String> description) {
                    this.description = description;
                }

                private ArrayList<String> label;

                public ArrayList<String> getLabel() {
                    return this.label;
                }

                public void setLabel(ArrayList<String> label) {
                    this.label = label;
                }
            }
        }
    }
}
